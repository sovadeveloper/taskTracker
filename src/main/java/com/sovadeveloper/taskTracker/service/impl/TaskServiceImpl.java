package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.TaskDTO;
import com.sovadeveloper.taskTracker.entity.Task;
import com.sovadeveloper.taskTracker.mapper.TaskMapper;
import com.sovadeveloper.taskTracker.repository.TaskRepository;
import com.sovadeveloper.taskTracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO create(Task task) {
        validate(task);
        return TaskMapper.INSTANCE.toDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO edit(Task task) {
        validate(task);
        taskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("Задачи с таким ID не существует"));
        return TaskMapper.INSTANCE.toDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO getById(Long id) {
        return TaskMapper.INSTANCE.toDTO(taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задачи с таким ID не существует")));
    }

    @Override
    public List<TaskDTO> getAll() {
        return TaskMapper.INSTANCE.listToDTO(taskRepository.findAll());
    }

    @Override
    public Page<TaskDTO> getAllWithPagination(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        List<TaskDTO> taskDTOS = TaskMapper.INSTANCE.listToDTO(tasks.getContent());
        return new PageImpl<>(taskDTOS, pageable, tasks.getTotalElements());
    }

    @Override
    public Long delete(Long id) {
        taskRepository.deleteById(id);
        return id;
    }

    private void validate(Task task){
        if(task.getName().isEmpty() || task.getName().length() < 3){
            throw new RuntimeException("Название задачи не может быть пустым или быть короче 3 символов");
        }
        if(task.getDescription().isEmpty() || task.getDescription().length() < 3){
            throw new RuntimeException("Описание задачи не может быть пустым или быть короче 3 символов");
        }
        if(task.getDateStart().isAfter(task.getDateEnd())){
            throw new RuntimeException("Дата начала не может быть позже, чем дата окончания");
        }
    }
}
