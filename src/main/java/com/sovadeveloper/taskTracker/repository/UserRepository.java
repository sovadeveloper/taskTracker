package com.sovadeveloper.taskTracker.repository;

import com.sovadeveloper.taskTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
