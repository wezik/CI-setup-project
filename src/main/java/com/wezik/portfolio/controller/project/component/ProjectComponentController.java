package com.wezik.portfolio.controller.project.component;

import com.wezik.portfolio.config.project.ProjectConfig;
import com.wezik.portfolio.controller.AppResponse;
import com.wezik.portfolio.dto.project.ProjectComponentDto;
import com.wezik.portfolio.mapper.project.ProjectComponentMapper;
import com.wezik.portfolio.model.project.Project;
import com.wezik.portfolio.model.project.ProjectComponent;
import com.wezik.portfolio.service.project.ProjectComponentService;
import com.wezik.portfolio.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("project/component")
@RestController
public class ProjectComponentController {

    private final ProjectService projectService;
    private final ProjectComponentService projectComponentService;

    private final ProjectComponentMapper projectComponentMapper;

    private final ProjectConfig projectConfig;

    @GetMapping
    public AppResponse getComponents() {
        final List<ProjectComponentDto> components = projectComponentService.getProjectComponents().stream()
                .map(projectComponentMapper::mapToDto)
                .collect(Collectors.toList());
        return AppResponse.statusOK(components);
    }

    @GetMapping("/{code}")
    public AppResponse getComponent(@PathVariable("code") String code) {
        final Optional<ProjectComponent> componentOptional = projectComponentService.findProjectComponent(code);
        return componentOptional.isEmpty() ?
                AppResponse.statusNotFound("Component not found") : AppResponse.statusOK(projectComponentMapper.mapToDto(componentOptional.get()));
    }

    @PostMapping
    public AppResponse createComponent(@RequestBody ProjectComponentDto dto) {
        final int codeLimit = projectConfig.getLengthLimitForCode();
        final int nameLimit = projectConfig.getLengthLimitForName();

        final Optional<Project> optionalProject = projectService.findProject(dto.getProjectCode());

        if (optionalProject.isEmpty()) {
            return AppResponse.statusBadRequest("Project of this code doesn't exists");
        } else if (projectComponentService.projectComponentExists(dto.getCode())) {
            return AppResponse.statusBadRequest("Component of this code already exists");
        } else if (dto.getCode().length() > codeLimit) {
            return AppResponse.statusBadRequest("Component code can't exceed " + codeLimit + " letters");
        } else if (dto.getName().length() > nameLimit) {
            return AppResponse.statusBadRequest("Component name can't exceed " + nameLimit + " letters");
        }

        final Project project = optionalProject.get();

        if (project.getComponents().size() >= 1 && !project.isMultiComponent()) {
            return AppResponse.statusBadRequest("This project can't contain more components");
        }

        final ProjectComponent component = projectComponentMapper.mapDtoToProjectComponent(dto, project);
        return AppResponse.statusOK(projectComponentService.saveProjectComponent(component));
    }

    @DeleteMapping("/{code}")
    public AppResponse deleteComponent(@PathVariable("code") String code) {
        projectComponentService.deleteProjectComponent(code);
        return AppResponse.statusOK();
    }
}
