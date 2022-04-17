package com.wezik.portfolio.service.project;

import com.wezik.portfolio.dto.project.Project;
import com.wezik.portfolio.dto.project.ProjectComponent;
import com.wezik.portfolio.repository.project.ProjectComponentRepository;
import com.wezik.portfolio.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectComponentRepository projectComponentRepository;

    public Collection<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> findProject(String code) {
        return projectRepository.findById(code.toUpperCase());
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(String code) {
        projectRepository.deleteById(code.toUpperCase());
    }

    public boolean projectExists(String code) {
        return projectRepository.existsById(code.toUpperCase());
    }

    public Collection<ProjectComponent> getProjectComponents() {
        return projectComponentRepository.findAll();
    }

    public Optional<ProjectComponent> findProjectComponent(Long id) {
        return projectComponentRepository.findById(id);
    }

    public ProjectComponent saveProjectComponent(ProjectComponent projectComponent) {
        return projectComponentRepository.save(projectComponent);
    }

}
