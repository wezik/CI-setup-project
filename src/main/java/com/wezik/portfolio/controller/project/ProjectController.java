package com.wezik.portfolio.controller.project;

import com.wezik.portfolio.config.project.ProjectConfig;
import com.wezik.portfolio.controller.AppResponse;
import com.wezik.portfolio.dto.project.ProjectComponentDto;
import com.wezik.portfolio.dto.project.ProjectDto;
import com.wezik.portfolio.mapper.project.ProjectComponentMapper;
import com.wezik.portfolio.mapper.project.ProjectMapper;
import com.wezik.portfolio.model.project.Project;
import com.wezik.portfolio.model.project.ProjectComponent;
import com.wezik.portfolio.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("project")
public class ProjectController {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;
    private final ProjectComponentMapper projectComponentMapper;

    private final ProjectConfig projectConfig;

    @GetMapping
    public AppResponse getProjects() {
        return AppResponse.statusOK(projectService.getProjects());
    }

    @GetMapping("/{code}")
    public AppResponse getProject(@PathVariable("code") String code) {
        final Optional<Project> optionalProject = projectService.findProject(code.toUpperCase());
        return optionalProject.isEmpty() ?
                AppResponse.statusNotFound("Project not found") : AppResponse.statusOK(optionalProject.get());
    }

    @PostMapping
    public AppResponse createProject(@RequestBody ProjectDto dto) {
        final int codeLimit = projectConfig.getLengthLimitForCode();
        final int nameLimit = projectConfig.getLengthLimitForName();

        if (projectService.projectExists(dto.getCode())) {
            return AppResponse.statusBadRequest("Project of this code already exists");
        } else if (dto.getCode().length() > codeLimit) {
            return AppResponse.statusBadRequest("Project code can't exceed " + codeLimit + " letters");
        } else if (dto.getName().length() > nameLimit) {
            return AppResponse.statusBadRequest("Project name can't exceed " + nameLimit + " letters");
        }

        final Project project = projectMapper.mapDtoToProject(dto);
        project.getComponents().add(this.createDefaultComponent(project));
        return AppResponse.statusOK(projectService.saveProject(project));
    }

    @DeleteMapping("/{code}")
    public AppResponse deleteProject(@PathVariable("code") String code) {
        projectService.deleteProject(code.toUpperCase());
        return AppResponse.statusOK();
    }

    private ProjectComponent createDefaultComponent(Project project) {
        return projectComponentMapper.mapDtoToProjectComponent(new ProjectComponentDto(
                project.getCode(),
                project.getName(),
                project.getDescription(),
                project.getCode()), project);
    }
}
