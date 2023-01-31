package com.sovadeveloper.taskTracker.mapper;

import com.sovadeveloper.taskTracker.dto.TagDTO;
import com.sovadeveloper.taskTracker.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    TagDTO toDTO(Tag tag);
    List<TagDTO> listToDTO(List<Tag> tags);
}
