package com.devboard.model;

public class DashboardStats {
    private long totalTasks;
    private long completedTasks;
    private long activeTasks;
    private long urgentTasks;

    public DashboardStats(long totalTasks, long completedTasks, long activeTasks, long urgentTasks) {
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.activeTasks = activeTasks;
        this.urgentTasks = urgentTasks;
    }

    // Getters
    public long getTotalTasks() { return totalTasks; }
    public long getCompletedTasks() { return completedTasks; }
    public long getActiveTasks() { return activeTasks; }
    public long getUrgentTasks() { return urgentTasks; }
}
