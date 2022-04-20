package com.wezik.portfolio.mapper.project;

import com.wezik.portfolio.dto.project.ProjectComponentDto;
import com.wezik.portfolio.model.project.Project;
import com.wezik.portfolio.model.project.ProjectComponent;
import org.springframework.stereotype.Service;

@Service
public class ProjectComponentMapper {

    public ProjectComponent mapDtoToProjectComponent(ProjectComponentDto dto, Project project) {
        return new ProjectComponent(
                dto.getCode(),
                dto.getName(),
                dto.getDescription(),
                project
        );
    }

    public ProjectComponentDto mapToDto(ProjectComponent projectComponent) {
        return new ProjectComponentDto(
                projectComponent.getCode(),
                projectComponent.getName(),
                projectComponent.getDescription(),
                projectComponent.getProject().getCode()
        );
    }
}
