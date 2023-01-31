package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.UserDTO;
import com.sovadeveloper.taskTracker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "grade", source = "grade")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "team", source = "team")
    UserDTO toDTO(User user);
    List<UserDTO> listToDTO(List<User> users);
}
