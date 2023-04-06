package com.sovadeveloper.taskTracker.jwt;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;

    private String password;

}