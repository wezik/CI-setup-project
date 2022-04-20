package com.wezik.portfolio.model.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @Column(nullable = false, length = 14)
    private String code;
    @Column(length = 24)
    private String name;
    private String description;
    private boolean isMultiComponent;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectComponent> components;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectImage> images;
}
