package com.sovadeveloper.taskTracker.algorithm;

import com.sovadeveloper.taskTracker.entity.*;
import com.sovadeveloper.taskTracker.repository.SprintRepository;
import com.sovadeveloper.taskTracker.repository.StatusRepository;
import com.sovadeveloper.taskTracker.repository.TaskRepository;
import com.sovadeveloper.taskTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BinPacking {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public void packingFreeTasks(Sprint sprint){
        Status inWork = statusRepository.findByName("В работе");
        List<Task> tasksBySprint = taskRepository
                .findAllBySprintAndStatusAndExecutor(sprint, statusRepository.findByName("Открыт"), null);
        tasksBySprint.sort((o1, o2) -> {
            Priority p1 = o1.getPriority();
            Priority p2 = o2.getPriority();
            return p2.getName().compareTo(p1.getName());
        });
        for(Task task: tasksBySprint){
            List<User> curUsers = detectSuitableUsersByPosition(task);
            int bestScore = 0;
            User bestUser = null;
            for(User user: curUsers){
                List<Task> tasksInWorkForCurrentUser =
                        taskRepository.findAllBySprintAndStatusAndExecutor
                                (sprint, statusRepository.findByName("В работе"), user);
                if(tasksInWorkForCurrentUser.size() < (optimalUsersToTasks(tasksBySprint.size(), curUsers.size()) + 1) ||
                        tasksInWorkForCurrentUser.isEmpty()){
                    if(bestScore < calculateUserScore(user, task)){
                        bestScore = calculateUserScore(user, task);
                        bestUser = user;
                    }
                }
            }
            task.setExecutor(bestUser);
            if(task.getExecutor() != null){
                task.setStatus(inWork);
            }
            taskRepository.save(task);
        }
    }

    private List<User> detectSuitableUsersByPosition(Task task){
        List<User> curUsers = new ArrayList<>();
        switch (task.getTag().getName()) {
            case "Разработка":
                curUsers = userRepository.findAllByPosition("Разработчик");
                break;
            case "Аналитика":
                curUsers = userRepository.findAllByPosition("Аналитик");
                break;
            case "Тестирование":
                curUsers = userRepository.findAllByPosition("Тестировщик");
                break;
        }
        return curUsers;
    }

    private int calculateUserScore(User user, Task task){
        int resU = 1;
        if(user.getGrade().equals("Junior")){
            resU = 1;
        }else if(user.getGrade().equals("Middle")){
            resU = 2;
        }else if(user.getGrade().equals("Senior")){
            resU = 3;
        }
        int resT = 1;
        if(task.getPriority().getName().equals("Низкий")){
            resT = 1;
        }else if(task.getPriority().getName().equals("Средний")){
            resT = 2;
        }else if(task.getPriority().getName().equals("Высокий")){
            resT = 3;
        }
        return resU * resT;
    }

    private int optimalUsersToTasks(int countTasks, int countUsers){
        return countTasks / countUsers;
    }
}
