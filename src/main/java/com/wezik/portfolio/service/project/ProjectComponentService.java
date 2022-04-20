package com.wezik.portfolio.service.project;

import com.wezik.portfolio.model.project.ProjectComponent;
import com.wezik.portfolio.repository.project.ProjectComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectComponentService {

    private final ProjectComponentRepository projectComponentRepository;

    public Collection<ProjectComponent> getProjectComponents() {
        return projectComponentRepository.findAll();
    }

    public Optional<ProjectComponent> findProjectComponent(String code) {
        return projectComponentRepository.findById(code.toUpperCase());
    }

    public ProjectComponent saveProjectComponent(ProjectComponent projectComponent) {
        return projectComponentRepository.save(projectComponent);
    }

    public void deleteProjectComponent(String code) {
        projectComponentRepository.deleteById(code.toUpperCase());
    }

    public boolean projectComponentExists(String code) {
        return projectComponentRepository.existsById(code.toUpperCase());
    }
}
