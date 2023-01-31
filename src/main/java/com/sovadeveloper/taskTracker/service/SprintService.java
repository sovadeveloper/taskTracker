package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.SprintDTO;
import com.sovadeveloper.taskTracker.entity.Sprint;

import java.util.List;

public interface SprintService {
    SprintDTO create(Sprint sprint);
    SprintDTO edit(Sprint sprint);
    SprintDTO getById(Long id);
    List<SprintDTO> getAll();
    Long delete(Long id);
}
