package com.sovadeveloper.taskTracker.algorithm;

import com.sovadeveloper.taskTracker.algorithm.bab.TaskAllocation;
import com.sovadeveloper.taskTracker.entity.Sprint;
import com.sovadeveloper.taskTracker.entity.Task;
import com.sovadeveloper.taskTracker.entity.User;
import com.sovadeveloper.taskTracker.repository.SprintRepository;
import com.sovadeveloper.taskTracker.repository.TaskRepository;
import com.sovadeveloper.taskTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BinPacking {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;

    public TaskAllocation packingFreeTasks(Long sprintId){
        TaskAllocation taskAllocation = new TaskAllocation();
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Данный спринт не найден"));
        List<Task> tasksBySprint = taskRepository.findAllBySprintAndExecutor(sprint, null);
        Comparator<Task> comparator = Comparator.comparing(Task::getStoryPoint);
        tasksBySprint.sort(comparator.reversed());
        taskAllocation.setTasks(tasksBySprint);
        for(Task task: tasksBySprint){
            List<User> curUsers = detectSuitableUsersByPosition(task);
            int bestScore = 0;
            User bestUser = null;
            for(User user: curUsers){
                if(sprint.getNumber() == 1){
                    List<Task> tasksInWorkForCurrentUser = taskRepository.findAllBySprintAndExecutor(sprint, user);
                    if(tasksInWorkForCurrentUser.size() < (optimalUsersToTasks(tasksBySprint.size(), curUsers.size()) + 1) ||
                            tasksInWorkForCurrentUser.isEmpty()){
                        if(bestScore < calculateUserScore(user, task)){
                            bestScore = calculateUserScore(user, task);
                            bestUser = user;
                        }
                    }
                }else{
                    Sprint prevSprint = sprintRepository
                            .findByNumberAndProject(sprint.getNumber() - 1, sprint.getProject());
                    if(calculateSumOfStoryPointByUserInCurrentSprint(taskAllocation, user)
                            <= calculateSumOfStoryPointByUserInSprint(user, prevSprint)){
                        if(bestScore < calculateUserScore(user, task)){
                            bestScore = calculateUserScore(user, task);
                            bestUser = user;
                        }
                    }
                }
            }
            taskAllocation.getUsers().add(bestUser);
            taskAllocation.setValue(taskAllocation.getValue() + bestScore);
        }
        return taskAllocation;
    }

    private int calculateSumOfStoryPointByUserInSprint(User user, Sprint sprint){
        List<Task> tasks = taskRepository.findAllBySprintAndExecutor(sprint, user);
        return tasks.stream()
                .mapToInt(Task::getStoryPoint)
                .sum();
    }

    private int calculateSumOfStoryPointByUserInCurrentSprint(TaskAllocation taskAllocation, User curUser){
        int index = 0;
        int res = 0;
        for(User user: taskAllocation.getUsers()){
            if(user == curUser){
                Task task = taskAllocation.getTasks().get(index);
                res += task.getStoryPoint();
            }
            index++;
        }
        return res;
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
