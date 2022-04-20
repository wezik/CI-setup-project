package com.wezik.portfolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String code;
    public String getCode() {
        return code.toUpperCase();
    }
    private String name;
    private String description;
    private boolean isMultiComponent;
}
