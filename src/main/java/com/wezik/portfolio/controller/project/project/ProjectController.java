package com.wezik.portfolio.controller.project.project;

import com.wezik.portfolio.config.project.ProjectConfig;
import com.wezik.portfolio.controller.AppResponse;
import com.wezik.portfolio.dto.project.Project;
import com.wezik.portfolio.dto.project.ProjectComponent;
import com.wezik.portfolio.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/project")
@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectConfig projectConfig;

    @GetMapping
    public AppResponse getProjects() {
        return AppResponse.statusOK(projectService.getProjects());
    }

    @GetMapping("/{code}")
    public AppResponse getProject(@PathVariable("code") String code) {
        Optional<Project> optionalProject = projectService.findProject(code.toUpperCase());
        if (optionalProject.isPresent()) {
            return AppResponse.statusOK(optionalProject.get());
        }
        return AppResponse.statusNotFound("Project not found");
    }

    @PostMapping
    public AppResponse addProject(@RequestBody AddProjectForm form) {
        final String code = form.getCode().toUpperCase();
        final int codeLimit = projectConfig.getLengthLimitForCode();
        final int nameLimit = projectConfig.getLengthLimitForName();

        if (projectService.projectExists(code)) {
            return AppResponse.statusBadRequest("Project of this code already exists");
        } else if (code.length() > codeLimit)
            return AppResponse.statusBadRequest("Project code can't exceed " + codeLimit + " letters");
        else if (form.getName().length() > nameLimit) {
            return AppResponse.statusBadRequest("Project name can't exceed " + nameLimit + " letters");
        }

        final Project project = new Project(code, form.getName(), form.getDescription(), false, new ArrayList<>());
        final ProjectComponent component = new ProjectComponent(null, form.getName(), form.getDescription(), project);

        final Project response = projectService.saveProject(project);
        projectService.saveProjectComponent(component);

        return AppResponse.statusOK(response);
    }

    @PutMapping("/{code}/multicomp")
    public AppResponse makeProjectMultiComponent(@PathVariable String code,
                                                 @RequestParam(value = "enable", defaultValue = "true") boolean enable) {

        final Optional<Project> projectOptional = projectService.findProject(code);

        if (projectOptional.isEmpty()) {
            return AppResponse.statusNotFound("Project not found");
        }

        final Project project = projectOptional.get();
        project.setMultiComponent(enable);

        return AppResponse.statusOK(projectService.saveProject(project));
    }

    @GetMapping("/{code}/multicomp")
    public AppResponse makeProjectMultiComponent(@PathVariable String code) {

        final Optional<Project> projectOptional = projectService.findProject(code);

        if (projectOptional.isEmpty()) {
            return AppResponse.statusNotFound("Project not found");
        }

        return AppResponse.statusOK(projectOptional.get().isMultiComponent());
    }

    @DeleteMapping("/{code}")
    public AppResponse deleteProject(@PathVariable("code") String key) {
        projectService.deleteProject(key);
        return AppResponse.statusOK(projectService.getProjects());
    }
}
