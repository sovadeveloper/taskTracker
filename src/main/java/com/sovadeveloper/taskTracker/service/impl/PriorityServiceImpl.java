package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.PriorityDTO;
import com.sovadeveloper.taskTracker.entity.Priority;
import com.sovadeveloper.taskTracker.mapper.PriorityMapper;
import com.sovadeveloper.taskTracker.repository.PriorityRepository;
import com.sovadeveloper.taskTracker.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public PriorityDTO create(Priority priority) {
        validate(priority);
        return PriorityMapper.INSTANCE.toDTO(priorityRepository.save(priority));
    }

    @Override
    public PriorityDTO edit(Priority priority) {
        validate(priority);
        priorityRepository.findById(priority.getId())
                .orElseThrow(() -> new RuntimeException("Приоритета с таким ID не существует"));
        return PriorityMapper.INSTANCE.toDTO(priorityRepository.save(priority));
    }

    @Override
    public PriorityDTO getById(Long id) {
        return PriorityMapper.INSTANCE.toDTO(priorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Приоритета с таким ID не существует")));
    }

    @Override
    public PriorityDTO getByName(String name) {
        return PriorityMapper.INSTANCE.toDTO(priorityRepository.findByName(name));
    }

    @Override
    public List<PriorityDTO> getAll() {
        return PriorityMapper.INSTANCE.listToDTO(priorityRepository.findAll());
    }

    @Override
    public Long delete(Long id) {
        priorityRepository.deleteById(id);
        return id;
    }

    private void validate(Priority priority){
        if(priority.getName().isEmpty() || priority.getName().length() < 3){
            throw new RuntimeException("Название приоритета не может быть пустым и быть короче 3 символов");
        }
    }
}
