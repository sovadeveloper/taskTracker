package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.TaskDTO;
import com.sovadeveloper.taskTracker.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskDTO create(Task task);
    TaskDTO edit(Task task);
    TaskDTO getById(Long id);
    List<TaskDTO> getAll();
    Page<TaskDTO> getAllWithPagination(Pageable pageable);
    Long delete(Long id);
}
