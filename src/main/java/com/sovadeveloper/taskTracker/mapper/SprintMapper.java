package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.SprintDTO;
import com.sovadeveloper.taskTracker.entity.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SprintMapper {
    SprintMapper INSTANCE = Mappers.getMapper(SprintMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "dateStart", source = "dateStart")
    @Mapping(target = "dateEnd", source = "dateEnd")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "project", source = "project")
    SprintDTO toDTO(Sprint sprint);
    List<SprintDTO> listToDTO(List<Sprint> sprints);
}
