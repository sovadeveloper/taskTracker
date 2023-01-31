package com.sovadeveloper.taskTracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {
    private Long id;
    private String name;
    private String specialization;
    private ProjectDTO project;
}
