package com.sovadeveloper.taskTracker.algorithm.bab;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    private TaskAllocation taskAllocation;
    private Node parent;
    private int upperBound;
    private boolean complete;
    private int level;

    @Override
    public String toString() {
        return "Node{" +
                "taskAllocation=" + taskAllocation +
                ", parent=" + parent +
                ", upperBound=" + upperBound +
                ", complete=" + complete +
                ", level=" + level +
                '}';
    }
}
