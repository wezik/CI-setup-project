package com.wezik.portfolio.controller.project.image;

import com.wezik.portfolio.controller.AppResponse;
import com.wezik.portfolio.dto.project.ProjectImageDto;
import com.wezik.portfolio.mapper.project.ProjectImageMapper;
import com.wezik.portfolio.model.project.Project;
import com.wezik.portfolio.model.project.ProjectImage;
import com.wezik.portfolio.service.project.ProjectImageService;
import com.wezik.portfolio.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("project/img")
public class ProjectImageController {

    private final ProjectImageService projectImageService;
    private final ProjectService projectService;

    private final ProjectImageMapper projectImageMapper;

    @GetMapping
    public AppResponse getProjectImages() {
        return AppResponse.statusOK(projectImageService.getProjectImages());
    }

    @GetMapping("/{id}")
    public AppResponse getProjectImage(@PathVariable("id") Long id) {
        final Optional<ProjectImage> imageOptional = projectImageService.findProjectImage(id);
        return imageOptional.isEmpty() ?
                AppResponse.statusNotFound("Image not found") : AppResponse.statusOK(imageOptional.get());
    }

    @GetMapping(value = "/{id}/view")
    public @ResponseBody ResponseEntity<InputStreamResource> redirectToProjectImageUrl(@PathVariable("id") Long id) throws IOException {
        final Optional<ProjectImage> imageOptional = projectImageService.findProjectImage(id);
        if (imageOptional.isEmpty()) return ResponseEntity.notFound().build();
        final URL url = new URL(imageOptional.get().getUrl());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(url.openStream()));
    }

    @PostMapping
    public AppResponse createProjectImage(@RequestBody ProjectImageDto dto) {
        final Optional<Project> optionalProject = projectService.findProject(dto.getProjectCode());
        return optionalProject.isEmpty() ?
                AppResponse.statusBadRequest("Project of this code doesn't exist") :
                AppResponse.statusOK(
                        projectImageService.saveProjectImage(
                                projectImageMapper.mapDtoToProjectImage(dto, optionalProject.get())));
    }

    @DeleteMapping("/{id}")
    public AppResponse deleteProjectImage(@PathVariable("id") Long id) {
        projectImageService.deleteProjectImage(id);
        return AppResponse.statusOK();
    }
}
