package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.UserDTO;
import com.sovadeveloper.taskTracker.entity.User;
import com.sovadeveloper.taskTracker.repository.UserRepository;
import com.sovadeveloper.taskTracker.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    // TODO: тут надо подключить секурити
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(User user) {
        return null;
    }

    @Override
    public UserDTO edit(User user) {
        return null;
    }

    @Override
    public UserDTO getById(Long id) {
        return null;
    }

    @Override
    public UserDTO getByName(String name) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}
