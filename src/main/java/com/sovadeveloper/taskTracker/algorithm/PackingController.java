package com.sovadeveloper.taskTracker.algorithm;

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

    @GetMapping
    public void packing(@RequestBody Sprint sprint){
        binPacking.packingFreeTasks(sprint);
    }
}
