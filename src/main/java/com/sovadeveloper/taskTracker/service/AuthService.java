package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.jwt.JwtRequest;
import com.sovadeveloper.taskTracker.jwt.JwtResponse;

import java.nio.file.AccessDeniedException;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken) throws AccessDeniedException;

}
