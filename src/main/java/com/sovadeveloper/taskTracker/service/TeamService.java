package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.TeamDTO;
import com.sovadeveloper.taskTracker.entity.Team;

import java.util.List;

public interface TeamService {
    TeamDTO create(Team team);
    TeamDTO edit(Team team);
    TeamDTO getById(Long id);
    List<TeamDTO> getAll();
    Long delete(Long id);
}
