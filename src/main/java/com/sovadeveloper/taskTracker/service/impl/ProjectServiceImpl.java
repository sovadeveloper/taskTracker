package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.ProjectDTO;
import com.sovadeveloper.taskTracker.entity.Project;
import com.sovadeveloper.taskTracker.mapper.ProjectMapper;
import com.sovadeveloper.taskTracker.repository.ProjectRepository;
import com.sovadeveloper.taskTracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDTO create(Project project) {
        validate(project);
        return ProjectMapper.INSTANCE.toDTO(projectRepository.save(project));
    }

    @Override
    public ProjectDTO edit(Project project) {
        validate(project);
        projectRepository.findById(project.getId())
                .orElseThrow(() -> new RuntimeException("Проекта с таким ID не существует"));
        return ProjectMapper.INSTANCE.toDTO(projectRepository.save(project));
    }

    @Override
    public ProjectDTO getById(Long id) {
        return ProjectMapper.INSTANCE.toDTO(projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Проекта с таким ID не существует")));
    }

    @Override
    public ProjectDTO getByName(String name) {
        return ProjectMapper.INSTANCE.toDTO(projectRepository.findByName(name));
    }

    @Override
    public List<ProjectDTO> getAll() {
        return ProjectMapper.INSTANCE.listToDTO(projectRepository.findAll());
    }

    @Override
    public Page<ProjectDTO> getAllWithPagination(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        List<ProjectDTO> projectDTOS = ProjectMapper.INSTANCE.listToDTO(projects.getContent());
        return new PageImpl<>(projectDTOS, pageable, projects.getTotalElements());
    }

    @Override
    public Long delete(Long id) {
        projectRepository.deleteById(id);
        return id;
    }

    private void validate(Project project){
        if(project.getName().isEmpty() || project.getName().length() < 3){
            throw new RuntimeException("Название проекта не может быть пустым и быть короче 3 символов");
        }
        if(project.getDescription().isEmpty() || project.getDescription().length() < 10){
            throw new RuntimeException("Описание проекта не может быть пустым и быть короче 10 символов");
        }
    }
}
