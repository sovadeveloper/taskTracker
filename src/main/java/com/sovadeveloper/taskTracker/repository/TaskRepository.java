package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
