package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.StatusDTO;
import com.sovadeveloper.taskTracker.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatusMapper {
    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    StatusDTO toDTO(Status status);
    List<StatusDTO> listToDTO(List<Status> statuses);
}
