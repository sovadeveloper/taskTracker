package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query(value = "SELECT * FROM statuses WHERE lower(name) = lower(:name)", nativeQuery = true)
    Status findByName(@Param("name") String name);
}
