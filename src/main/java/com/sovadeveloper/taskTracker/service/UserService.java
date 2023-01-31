package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.UserDTO;
import com.sovadeveloper.taskTracker.entity.User;

public interface UserService {
    UserDTO create(User user);
    UserDTO edit(User user);
    UserDTO getById(Long id);
    UserDTO getByName(String name);
    Long delete(Long id);
}
