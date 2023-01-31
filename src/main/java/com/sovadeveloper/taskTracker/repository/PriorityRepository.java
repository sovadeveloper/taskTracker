package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    @Query(value = "SELECT * FROM priorities WHERE lower(name) = lower(:name)", nativeQuery = true)
    Priority findByName(@Param("name") String name);
}
