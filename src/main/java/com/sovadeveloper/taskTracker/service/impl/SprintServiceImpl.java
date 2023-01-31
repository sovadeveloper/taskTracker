package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.SprintDTO;
import com.sovadeveloper.taskTracker.entity.Sprint;
import com.sovadeveloper.taskTracker.mapper.SprintMapper;
import com.sovadeveloper.taskTracker.repository.SprintRepository;
import com.sovadeveloper.taskTracker.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public SprintDTO create(Sprint sprint) {
        validate(sprint);
        return SprintMapper.INSTANCE.toDTO(sprintRepository.save(sprint));
    }

    @Override
    public SprintDTO edit(Sprint sprint) {
        validate(sprint);
        sprintRepository.findById(sprint.getId())
                .orElseThrow(() -> new RuntimeException("Спринта с таким ID не существует"));
        return SprintMapper.INSTANCE.toDTO(sprintRepository.save(sprint));
    }

    @Override
    public SprintDTO getById(Long id) {
        return SprintMapper.INSTANCE.toDTO(sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Спринта с таким ID не существует")));
    }

    @Override
    public List<SprintDTO> getAll() {
        return SprintMapper.INSTANCE.listToDTO(sprintRepository.findAll());
    }

    @Override
    public Long delete(Long id) {
        sprintRepository.deleteById(id);
        return id;
    }

    private void validate(Sprint sprint){
        // TODO: сделать разделение на POST и PUT для проверки по ID
//        Sprint persistentSprint = sprintRepository.findById(sprint.getId())
//                .orElseThrow(() -> new RuntimeException("Спринта с таким ID не существует"));
//        if(persistentSprint != null){
//            throw new RuntimeException("Номер спринта должен быть уникальным");
//        }
        if(sprint.getDateStart().isAfter(sprint.getDateEnd())){
            throw new RuntimeException("Дата начала не может быть позже, чем дата окончания");
        }
    }
}
