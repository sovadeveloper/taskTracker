package com.sovadeveloper.taskTracker.service.impl;

import com.sovadeveloper.taskTracker.dto.TeamDTO;
import com.sovadeveloper.taskTracker.entity.Team;
import com.sovadeveloper.taskTracker.mapper.TeamMapper;
import com.sovadeveloper.taskTracker.repository.TeamRepository;
import com.sovadeveloper.taskTracker.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamDTO create(Team team) {
        validate(team);
        return TeamMapper.INSTANCE.toDTO(teamRepository.save(team));
    }

    @Override
    public TeamDTO edit(Team team) {
        validate(team);
        teamRepository.findById(team.getId())
                .orElseThrow(() -> new RuntimeException("Команда с таким ID не существует"));
        return TeamMapper.INSTANCE.toDTO(teamRepository.save(team));
    }

    @Override
    public TeamDTO getById(Long id) {
        return TeamMapper.INSTANCE.toDTO(teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Команда с таким ID не существует")));
    }

    @Override
    public List<TeamDTO> getAll() {
        return TeamMapper.INSTANCE.listToDTO(teamRepository.findAll());
    }

    @Override
    public Long delete(Long id) {
        teamRepository.deleteById(id);
        return id;
    }

    private void validate(Team team){
        if(team.getName().isEmpty() || team.getName().length() < 3){
            throw new RuntimeException("Название команды не может быть пустым или быть короче 3 символов");
        }
        if(team.getSpecialization().isEmpty() || team.getSpecialization().length() < 3){
            throw new RuntimeException("Специализация команды не может быть пустой или быть короче 3 символов");
        }
    }
}
