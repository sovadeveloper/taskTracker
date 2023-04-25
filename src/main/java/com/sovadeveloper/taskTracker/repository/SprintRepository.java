package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Project;
import com.sovadeveloper.taskTracker.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    Sprint findByNumberAndProject(int number, Project project);
    List<Sprint> findByProject_Id(Long projectId);
}
