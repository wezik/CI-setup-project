package com.wezik.portfolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectImageDto {
    private Long id;
    private String title;
    private String description;
    private String url;
    private String projectCode;
}
