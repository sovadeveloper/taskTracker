package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByPosition(String position);
}
