package com.wezik.portfolio.mapper.project;

import com.wezik.portfolio.dto.project.ProjectDto;
import com.wezik.portfolio.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProjectMapper {

    public Project mapDtoToProject(ProjectDto dto) {
        return new Project(
                dto.getCode(),
                dto.getName(),
                dto.getDescription(),
                dto.isMultiComponent(),
                new ArrayList<>()
        );
    }
}
