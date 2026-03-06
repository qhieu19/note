package com.devboard.controller;

import com.devboard.model.Activity;
import com.devboard.model.Deadline;
import com.devboard.model.DashboardStats;
import com.devboard.service.TaskService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final TaskService taskService;

    public DashboardController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/stats")
    public DashboardStats getStats() {
        return taskService.getDashboardStats();
    }

    @GetMapping("/activities")
    public List<Activity> getActivities() {
        return taskService.getAllActivities();
    }

    @GetMapping("/deadlines")
    public List<Deadline> getDeadlines() {
        return taskService.getAllDeadlines();
    }
}
