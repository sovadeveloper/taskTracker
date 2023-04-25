package com.sovadeveloper.taskTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "project")
    private List<Team> teams;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "project")
    private List<Sprint> sprints;
}
