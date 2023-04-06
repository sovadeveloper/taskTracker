package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Sprint;
import com.sovadeveloper.taskTracker.entity.Status;
import com.sovadeveloper.taskTracker.entity.Task;
import com.sovadeveloper.taskTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllBySprintAndStatusAndExecutor(Sprint sprint, Status status, User user);
}
