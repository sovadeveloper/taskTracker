package com.sovadeveloper.taskTracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SprintDTO {
    private Long id;
    private int number;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String status;
    private ProjectDTO project;
}
