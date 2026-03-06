package com.devboard.service;

import com.devboard.model.Activity;
import com.devboard.model.Deadline;
import com.devboard.model.Task;
import com.devboard.model.DashboardStats;
import com.devboard.repository.ActivityRepository;
import com.devboard.repository.DeadlineRepository;
import com.devboard.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ActivityRepository activityRepository;
    private final DeadlineRepository deadlineRepository;

    public TaskService(TaskRepository taskRepository,
            ActivityRepository activityRepository,
            DeadlineRepository deadlineRepository) {
        this.taskRepository = taskRepository;
        this.activityRepository = activityRepository;
        this.deadlineRepository = deadlineRepository;
    }

    @PostConstruct
    public void init() {
        if (taskRepository.count() == 0) {
            // Seed Tasks
            taskRepository.saveAll(Arrays.asList(
                    new Task(UUID.randomUUID().toString(), "Integrate Firebase Auth", "high",
                            Arrays.asList("be", "sec"), "TODO", 0, "Mar 12", 4),
                    new Task(UUID.randomUUID().toString(), "Design System Audit", "med", Arrays.asList("fe", "ui"),
                            "IN_PROGRESS", 65, "Mar 08", 12),
                    new Task(UUID.randomUUID().toString(), "Database Migration", "high", Arrays.asList("be", "ops"),
                            "TODO", 0, "Mar 15", 2),
                    new Task(UUID.randomUUID().toString(), "API Documentation", "low", Arrays.asList("be", "docs"),
                            "DONE", 100, "Mar 01", 8),
                    new Task(UUID.randomUUID().toString(), "User Profile UI", "med", Arrays.asList("fe"), "TODO", 0,
                            "Mar 18", 5),
                    new Task(UUID.randomUUID().toString(), "Search Functionality", "high", Arrays.asList("be", "fe"),
                            "IN_PROGRESS", 30, "Mar 20", 7),
                    new Task(UUID.randomUUID().toString(), "Dark Mode Toggle", "low", Arrays.asList("fe", "ui"), "DONE",
                            100, "Feb 25", 2),
                    new Task(UUID.randomUUID().toString(), "Analytics Dashboard", "med", Arrays.asList("be", "fe"),
                            "IN_PROGRESS", 50, "Mar 14", 10)));
        }

        if (activityRepository.count() == 0) {
            // Seed Activities
            activityRepository.saveAll(Arrays.asList(
                    new Activity(UUID.randomUUID().toString(), "You", "moved 'REST API CRUD' to In Progress",
                            "2 min ago", "var(--blue)"),
                    new Activity(UUID.randomUUID().toString(), "You", "completed 'Dockerfile setup'", "1 hr ago",
                            "var(--green)"),
                    new Activity(UUID.randomUUID().toString(), "CI/CD", "deployed to Render ✓", "3 hr ago",
                            "var(--amber)")));
        }

        if (deadlineRepository.count() == 0) {
            // Seed Deadlines
            deadlineRepository.saveAll(Arrays.asList(
                    new Deadline(UUID.randomUUID().toString(), "Firebase JWT verification", "security", "05", "mar",
                            "overdue", "high", true),
                    new Deadline(UUID.randomUUID().toString(), "Dashboard UI Firebase Auth", "frontend", "07", "mar",
                            "1 day left", "med", false),
                    new Deadline(UUID.randomUUID().toString(), "REST API CRUD Firestore", "backend", "08", "mar",
                            "2 days left", "high", false)));
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Deadline> getAllDeadlines() {
        return deadlineRepository.findAll();
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public Task createTask(Task task) {
        if (task.getId() == null) {
            task.setId(UUID.randomUUID().toString());
        }
        return taskRepository.save(task);
    }

    public DashboardStats getDashboardStats() {
        List<Task> tasks = taskRepository.findAll();
        long total = tasks.size();
        long done = tasks.stream().filter(t -> "DONE".equals(t.getStatus())).count();
        long active = total - done;
        long urgent = tasks.stream().filter(t -> "high".equals(t.getPriority())).count();

        return new DashboardStats(total, done, active, urgent);
    }
}
