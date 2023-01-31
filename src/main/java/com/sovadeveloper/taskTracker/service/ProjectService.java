package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.ProjectDTO;
import com.sovadeveloper.taskTracker.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDTO create(Project project);
    ProjectDTO edit(Project project);
    ProjectDTO getById(Long id);
    ProjectDTO getByName(String name);
    List<ProjectDTO> getAll();
    Page<ProjectDTO> getAllWithPagination(Pageable pageable);
    Long delete(Long id);
}
