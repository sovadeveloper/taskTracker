package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.StatusDTO;
import com.sovadeveloper.taskTracker.entity.Status;

import java.util.List;

public interface StatusService {
    StatusDTO create(Status status);
    StatusDTO edit(Status status);
    StatusDTO getById(Long id);
    StatusDTO getByName(String name);
    List<StatusDTO> getAll();
    Long delete(Long id);
}
