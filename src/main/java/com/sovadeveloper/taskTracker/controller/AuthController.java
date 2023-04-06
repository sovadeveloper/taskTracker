package com.sovadeveloper.taskTracker.controller;

import com.sovadeveloper.taskTracker.dto.UserDTO;
import com.sovadeveloper.taskTracker.entity.User;
import com.sovadeveloper.taskTracker.jwt.JwtRequest;
import com.sovadeveloper.taskTracker.jwt.JwtResponse;
import com.sovadeveloper.taskTracker.mapper.UserMapper;
import com.sovadeveloper.taskTracker.service.AuthService;
import com.sovadeveloper.taskTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest){
        return authService.login(jwtRequest);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody User user){
        UserDTO userDTO = userService.create(user);
        return userDTO;
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) throws AccessDeniedException {
        return authService.refresh(refreshToken);
    }
}
