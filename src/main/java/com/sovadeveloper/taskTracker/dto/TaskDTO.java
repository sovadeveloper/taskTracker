package com.sovadeveloper.taskTracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private int storyPoint;
    private PriorityDTO priority;
    private StatusDTO status;
    private TagDTO tag;
    private SprintDTO sprint;
    private UserDTO creator;
    private UserDTO executor;
}
