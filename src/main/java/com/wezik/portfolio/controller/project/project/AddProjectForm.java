package com.wezik.portfolio.controller.project.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class AddProjectForm {
    private final String code;
    private final String name;
    private String description;
}
