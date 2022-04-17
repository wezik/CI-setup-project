package com.wezik.portfolio.controller.project.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class AddProjectComponentForm {
    private final String name;
    private final String description;
    private final String projectCode;
}
