package com.wezik.portfolio.service.project;

import com.wezik.portfolio.model.project.Project;
import com.wezik.portfolio.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

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
}
