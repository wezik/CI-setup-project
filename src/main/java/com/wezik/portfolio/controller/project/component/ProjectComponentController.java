package com.wezik.portfolio.controller.project.component;

import com.wezik.portfolio.controller.AppResponse;
import com.wezik.portfolio.dto.project.Project;
import com.wezik.portfolio.dto.project.ProjectComponent;
import com.wezik.portfolio.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/project/component")
@RestController
public class ProjectComponentController {

    private final ProjectService projectService;

    @GetMapping
    public AppResponse getComponents() {
        return AppResponse.statusOK(projectService.getProjectComponents());
    }

    @GetMapping("/{id}")
    public AppResponse getComponent(@PathVariable("id") Long id) {
        Optional<ProjectComponent> componentOptional = projectService.findProjectComponent(id);
        return componentOptional.isPresent()
                ? AppResponse.statusOK(componentOptional.get()) : AppResponse.statusNotFound("Component not found");
    }

    @PostMapping
    public AppResponse addComponent(@RequestBody AddProjectComponentForm form) {
        final Optional<Project> optionalProject = projectService.findProject(form.getProjectCode());

        if (optionalProject.isEmpty()) {
            return AppResponse.statusNotFound("Project not found");
        }

        final Project project = optionalProject.get();

        if (project.getComponents().size() >= 1 && !project.isMultiComponent()) {
            return AppResponse.statusBadRequest("This project can't contain more components");
        }

        final ProjectComponent component = new ProjectComponent(
                null,
                form.getName(),
                form.getDescription(),
                project);

        return AppResponse.statusOK(projectService.saveProjectComponent(component));
    }

    @DeleteMapping("/{project_code}/{id}")
    public AppResponse deleteComponent(@PathVariable("project_code") String projectCode, @PathVariable("id") Long id) {
        final Optional<Project> projectOptional = projectService.findProject(projectCode);

        if (projectOptional.isEmpty()) {
            return AppResponse.statusNotFound("Project not found");
        }

        final Project project = projectOptional.get();
        project.getComponents().removeIf(e -> e.getId().equals(id));

        return AppResponse.statusOK(projectService.saveProject(project));
    }
}
