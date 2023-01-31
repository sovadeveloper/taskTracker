package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.PriorityDTO;
import com.sovadeveloper.taskTracker.entity.Priority;

import java.util.List;

public interface PriorityService {
    PriorityDTO create(Priority priority);
    PriorityDTO edit(Priority priority);
    PriorityDTO getById(Long id);
    PriorityDTO getByName(String name);
    List<PriorityDTO> getAll();
    Long delete(Long id);
}
