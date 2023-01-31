package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.TagDTO;
import com.sovadeveloper.taskTracker.entity.Tag;
import com.sovadeveloper.taskTracker.mapper.TagMapper;
import com.sovadeveloper.taskTracker.repository.TagRepository;
import com.sovadeveloper.taskTracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public TagDTO create(Tag tag) {
        validate(tag);
        return TagMapper.INSTANCE.toDTO(tagRepository.save(tag));
    }

    @Override
    public TagDTO edit(Tag tag) {
        validate(tag);
        tagRepository.findById(tag.getId())
                .orElseThrow(() -> new RuntimeException("Тэга с таким ID не существует"));
        return TagMapper.INSTANCE.toDTO(tagRepository.save(tag));
    }

    @Override
    public TagDTO getById(Long id) {
        return TagMapper.INSTANCE.toDTO(tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Тэга с таким ID не существует")));
    }

    @Override
    public TagDTO getByName(String name) {
        return TagMapper.INSTANCE.toDTO(tagRepository.findByName(name));
    }

    @Override
    public List<TagDTO> getAll() {
        return TagMapper.INSTANCE.listToDTO(tagRepository.findAll());
    }

    @Override
    public Long delete(Long id) {
        tagRepository.deleteById(id);
        return id;
    }

    private void validate(Tag tag){
        if(tag.getName().isEmpty() || tag.getName().length() < 3){
            throw new RuntimeException("Название тэга не может быть пустым и быть короче 3 символов");
        }
    }
}
