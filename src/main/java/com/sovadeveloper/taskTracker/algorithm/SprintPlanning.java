package com.sovadeveloper.taskTracker.algorithm;

import com.sovadeveloper.taskTracker.entity.Sprint;
import com.sovadeveloper.taskTracker.entity.Task;
import com.sovadeveloper.taskTracker.repository.SprintRepository;
import com.sovadeveloper.taskTracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

@Service
@RequiredArgsConstructor
public class SprintPlanning {
    // если надо просто выплевывать число, то перебираем все таски для каждого спринта, таким образом соибраем массив стори поинтов для спринтов
    // если сохраняем, то в спринт добавить два поля: excPlan - ожидаемая нагрузка, curPlan - текущая нагрузка
    private final SprintRepository sprintRepository;
    private final TaskRepository taskRepository;

    public int iterationPlanning(Long projectId){
        List<Integer> storyPoints = getStoryPointsList(projectId);
        double mean = mathExpectation(storyPoints);
        double d = sqrt(distribution(storyPoints, mean));
        return (int) new NormalDistribution(mean, d).inverseCumulativeProbability(0.1);
    }

    private List<Integer> getStoryPointsList(Long projectId){
        List<Integer> storyPoints = new ArrayList<>();
        List<Sprint> sprints = sprintRepository.findByProject_Id(projectId);
        for(Sprint sprint: sprints){
            List<Task> tasks = taskRepository.findAllBySprint(sprint);
            int storyPointsForCurrentSprint = tasks.stream()
                    .mapToInt(Task::getStoryPoint)
                    .sum();
            storyPoints.add(storyPointsForCurrentSprint);
        }
        return storyPoints;
    }

    private double mathExpectation(List<Integer> storyPoints){
        return storyPoints.stream()
                .mapToDouble(a -> a)
                .sum() / storyPoints.size();
    }

    private double distribution(List<Integer> storyPoints, double mean){
        double dist = 0;
        for(Integer i: storyPoints){
            dist += pow(i - mean, 2);
        }
        return  dist / storyPoints.size();
    }
}
