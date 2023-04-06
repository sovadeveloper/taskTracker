package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.UserDTO;
import com.sovadeveloper.taskTracker.entity.Role;
import com.sovadeveloper.taskTracker.entity.User;
import com.sovadeveloper.taskTracker.mapper.UserMapper;
import com.sovadeveloper.taskTracker.repository.UserRepository;
import com.sovadeveloper.taskTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO create(User user) {
        if(user.getPassword().length() < 8){
            throw new RuntimeException("Пароль должен состоять минимум из 8 символов");
        }
        if(user.getUsername().length() < 1){
            throw new RuntimeException("Никнейм не может быть пустым");
        }
        User userFromDbByName = userRepository.findByUsername(user.getUsername());
        if(userFromDbByName != null){
            throw new RuntimeException("Пользователь с таким никнеймом уже существует");
        }
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.toDTO(userRepository.save(user));
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
