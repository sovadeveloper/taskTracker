package com.sovadeveloper.taskTracker.jwt;

import com.sovadeveloper.taskTracker.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtEntityFactory {

    public static JwtEntity create(User user){
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())));
    }

}
