package com.devboard.service;

import com.devboard.model.Task;
import com.devboard.model.DashboardStats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public TaskService() {
        // Mock initial data
        tasks.add(new Task(UUID.randomUUID().toString(), "Integrate Firebase Auth", "high", Arrays.asList("be", "sec"),
                "TODO", 0, "Mar 12", 4));
        tasks.add(new Task(UUID.randomUUID().toString(), "Design System Audit", "med", Arrays.asList("fe", "ui"),
                "IN_PROGRESS", 65, "Mar 08", 12));
        tasks.add(new Task(UUID.randomUUID().toString(), "Database Migration", "high", Arrays.asList("be", "ops"),
                "TODO", 0, "Mar 15", 2));
        tasks.add(new Task(UUID.randomUUID().toString(), "API Documentation", "low", Arrays.asList("be", "docs"),
                "DONE", 100, "Mar 01", 8));
        tasks.add(new Task(UUID.randomUUID().toString(), "User Profile UI", "med", Arrays.asList("fe"), "TODO", 0,
                "Mar 18", 5));
        tasks.add(new Task(UUID.randomUUID().toString(), "Search Functionality", "high", Arrays.asList("be", "fe"),
                "IN_PROGRESS", 30, "Mar 20", 7));
        tasks.add(new Task(UUID.randomUUID().toString(), "Dark Mode Toggle", "low", Arrays.asList("fe", "ui"), "DONE",
                100, "Feb 25", 2));
        tasks.add(new Task(UUID.randomUUID().toString(), "Analytics Dashboard", "med", Arrays.asList("be", "fe"),
                "IN_PROGRESS", 50, "Mar 14", 10));
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getTasksByStatus(String status) {
        return tasks.stream()
                .filter(t -> t.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public Task createTask(Task task) {
        if (task.getId() == null) {
            task.setId(UUID.randomUUID().toString());
        }
        tasks.add(task);
        return task;
    }

    public DashboardStats getDashboardStats() {
        long total = tasks.size();
        long done = tasks.stream().filter(t -> "DONE".equals(t.getStatus())).count();
        long active = total - done;
        long urgent = tasks.stream().filter(t -> "high".equals(t.getPriority())).count();

        return new DashboardStats(total, done, active, urgent);
    }
}
