package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.ProjectDTO;
import com.sovadeveloper.taskTracker.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    ProjectDTO toDTO(Project project);
    List<ProjectDTO> listToDTO(List<Project> projects);
}
