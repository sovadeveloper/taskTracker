package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.SprintDTO;
import com.sovadeveloper.taskTracker.dto.TaskDTO;
import com.sovadeveloper.taskTracker.entity.Sprint;
import com.sovadeveloper.taskTracker.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "dateStart", source = "dateStart")
    @Mapping(target = "dateEnd", source = "dateEnd")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "tag", source = "tag")
    @Mapping(target = "sprint", source = "sprint")
    @Mapping(target = "project", source = "project")
    @Mapping(target = "creator", source = "creator")
    @Mapping(target = "executor", source = "executor")
    TaskDTO toDTO(Task task);
    List<TaskDTO> listToDTO(List<Task> tasks);
}
