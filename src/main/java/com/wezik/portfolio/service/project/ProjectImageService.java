package com.wezik.portfolio.service.project;

import com.wezik.portfolio.model.project.ProjectImage;
import com.wezik.portfolio.repository.project.ProjectImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectImageService {

    private final ProjectImageRepository projectImageRepository;

    public Collection<ProjectImage> getProjectImages() {
        return projectImageRepository.findAll();
    }

    public Optional<ProjectImage> findProjectImage(Long id) {
        return projectImageRepository.findById(id);
    }

    public ProjectImage saveProjectImage(ProjectImage projectImage) {
        return projectImageRepository.save(projectImage);
    }

    public void deleteProjectImage(Long id) {
        projectImageRepository.deleteById(id);
    }
}
