package com.wezik.portfolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectComponentDto {
    private String code;
    public String getCode() {
        return code.toUpperCase();
    }
    private String name;
    private String description;
    private String projectCode;
    public String getProjectCode() {
        return projectCode.toUpperCase();
    }
}
