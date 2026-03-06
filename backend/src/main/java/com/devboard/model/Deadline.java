package com.devboard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "deadlines")
public class Deadline {
    @Id
    private String id;

    private String title;
    private String category;
    private String day;
    private String month;
    private String timeLeft;
    private String priority; // high, med, low
    private boolean isUrgent;

    public Deadline() {
    }

    public Deadline(String id, String title, String category, String day, String month, String timeLeft,
            String priority, boolean isUrgent) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.day = day;
        this.month = month;
        this.timeLeft = timeLeft;
        this.priority = priority;
        this.isUrgent = isUrgent;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }
}
