package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.PriorityDTO;
import com.sovadeveloper.taskTracker.entity.Priority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriorityMapper {
    PriorityMapper INSTANCE = Mappers.getMapper(PriorityMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    PriorityDTO toDTO(Priority priority);
    List<PriorityDTO> listToDTO(List<Priority> priorities);
}
