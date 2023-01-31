package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
