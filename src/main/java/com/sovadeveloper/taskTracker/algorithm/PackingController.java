package com.sovadeveloper.taskTracker.algorithm;

import com.sovadeveloper.taskTracker.algorithm.bab.BranchAndBound;
import com.sovadeveloper.taskTracker.algorithm.bab.TaskAllocation;
import com.sovadeveloper.taskTracker.entity.Sprint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/packing")
@RequiredArgsConstructor
public class PackingController {
    private final BinPacking binPacking;
    private final BranchAndBound branchAndBound;

    @GetMapping
    public void packing(@RequestBody Sprint sprint){
        binPacking.packingFreeTasks(sprint.getId());
    }

    @GetMapping("/bAb")
    public void packingBranchesAndBorders(@RequestBody Sprint sprint){
        TaskAllocation bpTaskAllocation = binPacking.packingFreeTasks(sprint.getId());
        TaskAllocation babTaskAllocation = branchAndBound.branchAndBound(bpTaskAllocation, sprint.getId());
        branchAndBound.saveResultInDB(babTaskAllocation.getUsers(), babTaskAllocation.getTasks());
    }
}
