package com.wezik.portfolio.model.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "project_components")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectComponent {
    @Id
    @Column(nullable = false, length = 14)
    private String code;
    @Column(length = 24)
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_code", nullable = false)
    @JsonIgnore
    private Project project;
}