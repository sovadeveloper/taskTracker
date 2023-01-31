package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
