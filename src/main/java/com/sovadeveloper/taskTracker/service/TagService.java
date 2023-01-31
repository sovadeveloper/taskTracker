package com.sovadeveloper.taskTracker.service;

import com.sovadeveloper.taskTracker.dto.TagDTO;
import com.sovadeveloper.taskTracker.entity.Tag;

import java.util.List;

public interface TagService {
    TagDTO create(Tag tag);
    TagDTO edit(Tag tag);
    TagDTO getById(Long id);
    TagDTO getByName(String name);
    List<TagDTO> getAll();
    Long delete(Long id);
}
