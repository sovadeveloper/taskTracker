package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.entity.User;
import com.sovadeveloper.taskTracker.jwt.JwtRequest;
import com.sovadeveloper.taskTracker.jwt.JwtResponse;
import com.sovadeveloper.taskTracker.jwt.JwtTokenProvider;
import com.sovadeveloper.taskTracker.repository.UserRepository;
import com.sovadeveloper.taskTracker.service.AuthService;
import com.sovadeveloper.taskTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = userRepository.findByUsername(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRole()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) throws AccessDeniedException {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
