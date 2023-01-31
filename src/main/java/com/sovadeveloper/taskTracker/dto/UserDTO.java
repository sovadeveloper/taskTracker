package com.sovadeveloper.taskTracker.dto;

import com.sovadeveloper.taskTracker.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private Role role;
    private String grade;
    private String position;
    private TeamDTO team;
}
