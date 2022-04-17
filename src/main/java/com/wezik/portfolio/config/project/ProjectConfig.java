package com.wezik.portfolio.config.project;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@EnableConfigurationProperties
public class ProjectConfig {

    @Value("${project.length.limit.name}")
    private int lengthLimitForName;

    @Value("${project.length.limit.code}")
    private int lengthLimitForCode;

}
