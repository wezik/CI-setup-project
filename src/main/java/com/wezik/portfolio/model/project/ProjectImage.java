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
@Table(name = "project_images")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String url;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_code")
    private Project project;
}
