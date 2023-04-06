package com.sovadeveloper.taskTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "grade", nullable = false)
    private String grade;

    @Column(name = "position", nullable = false)
    private String position;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "creator")
    private List<Task> tasksFromCreator;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "executor")
    private List<Task> tasksFromExecutor;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
