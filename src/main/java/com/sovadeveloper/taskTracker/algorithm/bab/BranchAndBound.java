package com.sovadeveloper.taskTracker.algorithm.bab;

import com.sovadeveloper.taskTracker.entity.Sprint;
import com.sovadeveloper.taskTracker.entity.Task;
import com.sovadeveloper.taskTracker.entity.User;
import com.sovadeveloper.taskTracker.repository.SprintRepository;
import com.sovadeveloper.taskTracker.repository.TaskRepository;
import com.sovadeveloper.taskTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Service
@RequiredArgsConstructor
public class BranchAndBound {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;

    public TaskAllocation branchAndBound(TaskAllocation initial, Long sprintId){
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Данный спринт не найден"));
        List<Task> tasks = taskRepository.findAllBySprintAndExecutor(sprint, null);
        TaskAllocation bestSolution = initial;
        Stack<Node> stack = new Stack<>();
        Node root = new Node(new TaskAllocation(new ArrayList<>(), tasks, 0),
                null, 0, false, 0);
        stack.push(root);
        while (!stack.isEmpty()){
            Node current = stack.pop();
            if(current.isComplete()){
                if(current.getTaskAllocation().getValue() > bestSolution.getValue()){
                    bestSolution = current.getTaskAllocation();
                }
            }else{
                Task task = tasks.get(current.getLevel());
                List<User> candidateExecutors = filterBySpecialization(task);
                Collections.shuffle(candidateExecutors);
                Sprint prevSprint = sprintRepository
                        .findByNumberAndProject(sprint.getNumber() - 1, sprint.getProject());
                for(User executor: candidateExecutors){
                    if(calculateSumOfStoryPointByUserInSprint(executor, sprint, prevSprint, current.getTaskAllocation())){
                        TaskAllocation newAllocation = current.getTaskAllocation().copy();
                        newAllocation.setNewData(executor, calculateUserScore(executor, task));
                        int newUpperBound = calculateUpperBound(newAllocation, tasks, candidateExecutors);
                        if(newUpperBound > bestSolution.getValue()){
                            Node newNode = new Node(
                                    newAllocation,
                                    current,
                                    newUpperBound,
                                    current.getLevel() + 1 == tasks.size(),
                                    current.getLevel() + 1
                            );
                            stack.push(newNode);
                            break;
                        }
                    }
                }
            }
        }
        return bestSolution;
    }

    public void saveResultInDB(List<User> users, List<Task> tasks){
        int index = 0;
        for(User user: users){
            if(index < tasks.size()){
                Task task = tasks.get(index);
                task.setExecutor(user);
                taskRepository.save(task);
                index++;
            }
        }
    }

    private int calculateUpperBound(TaskAllocation newAllocation, List<Task> tasks, List<User> candidateExecutors) {
        int res = newAllocation.getValue();
        for(Task task: tasks){
            if(!newAllocation.getUsers().contains(task)){
                res += chooseBestUser(candidateExecutors, task);
            }
        }
        return res;
    }

    private List<User> filterBySpecialization(Task task){
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

    private boolean calculateSumOfStoryPointByUserInSprint(User user, Sprint sprint, Sprint prevSprint,
                                                       TaskAllocation taskAllocation){
        boolean flag = false;
        List<Task> tasksForPrevSprint = taskRepository.findAllBySprintAndExecutor(prevSprint, user);
        int scoreOfPrevSprint = tasksForPrevSprint.stream()
                .mapToInt(Task::getStoryPoint)
                .sum();
        if(taskAllocation.getValue() <= scoreOfPrevSprint){
            flag = true;
        }
        return flag;
    }

    private int chooseBestUser(List<User> users, Task task){
        int bestScore = 0;
        for(User user: users){
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
            if(resT * resU > bestScore){
                bestScore = resT * resU;
            }
        }
        return bestScore;
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
}
