package com.devboard.model;

import java.util.List;

public class Task {
    private String id;
    private String title;
    private String priority; // high, med, low
    private List<String> tags;
    private String status; // TODO, IN_PROGRESS, DONE
    private Integer progress; // 0-100
    private String dueDate;
    private Integer subtaskCount;

    // Default constructor for JSON mapping
    public Task() {}

    public Task(String id, String title, String priority, List<String> tags, String status, Integer progress, String dueDate, Integer subtaskCount) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.tags = tags;
        this.status = status;
        this.progress = progress;
        this.dueDate = dueDate;
        this.subtaskCount = subtaskCount;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getProgress() { return progress; }
    public void setProgress(Integer progress) { this.progress = progress; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public Integer getSubtaskCount() { return subtaskCount; }
    public void setSubtaskCount(Integer subtaskCount) { this.subtaskCount = subtaskCount; }
}
