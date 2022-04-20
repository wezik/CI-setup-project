package com.wezik.portfolio.repository.project;

import com.wezik.portfolio.model.project.ProjectComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectComponentRepository extends JpaRepository<ProjectComponent, String> {
}