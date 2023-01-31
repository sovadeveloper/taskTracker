package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.TeamDTO;
import com.sovadeveloper.taskTracker.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "specialization", source = "specialization")
    @Mapping(target = "project", source = "project")
    TeamDTO toDTO(Team team);
    List<TeamDTO> listToDTO(List<Team> teams);
}
