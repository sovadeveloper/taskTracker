package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT * FROM tags WHERE lower(name) = lower(:name)", nativeQuery = true)
    Tag findByName(@Param("name") String name);
}
