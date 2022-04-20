package com.wezik.portfolio.mapper.project;

import com.wezik.portfolio.dto.project.ProjectImageDto;
import com.wezik.portfolio.model.project.Project;
import com.wezik.portfolio.model.project.ProjectImage;
import org.springframework.stereotype.Service;

@Service
public class ProjectImageMapper {

    public ProjectImage mapDtoToProjectImage(ProjectImageDto dto, Project project) {
        return new ProjectImage(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getUrl(),
                project
        );
    }
}
