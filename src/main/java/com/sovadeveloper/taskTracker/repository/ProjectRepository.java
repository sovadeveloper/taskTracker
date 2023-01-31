package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT * FROM projects WHERE lower(name) = lower(:name)", nativeQuery = true)
    Project findByName(@Param("name") String name);
}
