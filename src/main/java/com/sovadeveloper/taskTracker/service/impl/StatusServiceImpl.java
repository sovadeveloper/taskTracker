package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.StatusDTO;
import com.sovadeveloper.taskTracker.entity.Status;
import com.sovadeveloper.taskTracker.mapper.StatusMapper;
import com.sovadeveloper.taskTracker.repository.StatusRepository;
import com.sovadeveloper.taskTracker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public StatusDTO create(Status status) {
        validate(status);
        return StatusMapper.INSTANCE.toDTO(statusRepository.save(status));
    }

    @Override
    public StatusDTO edit(Status status) {
        validate(status);
        statusRepository.findById(status.getId())
                .orElseThrow(() -> new RuntimeException("Статуса с таким ID не существует"));
        return StatusMapper.INSTANCE.toDTO(statusRepository.save(status));
    }

    @Override
    public StatusDTO getById(Long id) {
        return StatusMapper.INSTANCE.toDTO(statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Статуса с таким ID не существует")));
    }

    @Override
    public StatusDTO getByName(String name) {
        return StatusMapper.INSTANCE.toDTO(statusRepository.findByName(name));
    }

    @Override
    public List<StatusDTO> getAll() {
        return StatusMapper.INSTANCE.listToDTO(statusRepository.findAll());
    }

    @Override
    public Long delete(Long id) {
        statusRepository.deleteById(id);
        return id;
    }

    private void validate(Status status){
        if(status.getName().isEmpty() || status.getName().length() < 3){
            throw new RuntimeException("Название статуса не может быть пустым и быть короче 3 символов");
        }
    }
}
