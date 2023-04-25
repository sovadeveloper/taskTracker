package com.sovadeveloper.taskTracker.algorithm.bab;

import com.sovadeveloper.taskTracker.entity.Task;
import com.sovadeveloper.taskTracker.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAllocation {
    private List<User> users = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private int value;

    public TaskAllocation copy(){
        return this;
    }

    public void setNewData(User user, int score){
        this.users.add(user);
        this.value += score;
    }

    @Override
    public String toString() {
        return "TaskAllocation{" +
                "value=" + value +
                '}';
    }
}
