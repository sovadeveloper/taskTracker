package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Sprint;
import com.sovadeveloper.taskTracker.entity.Status;
import com.sovadeveloper.taskTracker.entity.Task;
import com.sovadeveloper.taskTracker.entity.User;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllBySprintAndStatusAndExecutor(Sprint sprint, Status status, User user);
    List<Task> findAllBySprintAndExecutor(Sprint sprint, User executor);
    List<Task> findAllBySprint(Sprint sprint);
//    @Query(
//            value = "SELECT SUM(story_point) FROM tasks WHERE sprint_id = :sprintId AND executor_id = :executorId",
//            nativeQuery = true
//
//    )
//    int sumOfStoryPointsBySprintAndExecutor(Long sprintId, Long executorId);
}
