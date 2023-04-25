package com.sovadeveloper.taskTracker.algorithm;

import com.sovadeveloper.taskTracker.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planning")
@RequiredArgsConstructor
public class PlanningController {
    private final SprintPlanning sprintPlanning;

    @GetMapping
    public int packing(@RequestBody Project project){
        return sprintPlanning.iterationPlanning(project.getId());
    }
}
