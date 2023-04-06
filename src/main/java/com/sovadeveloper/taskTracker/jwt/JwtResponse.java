package com.sovadeveloper.taskTracker.jwt;

import lombok.Data;

@Data
public class JwtResponse {

    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;

}
