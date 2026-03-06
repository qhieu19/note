package com.devboard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    private String id;

    private String userName; // Changed from 'user' to avoid reserved keyword issues
    private String action;
    private String timeLabel;
    private String color; // hex or CSS variable

    public Activity() {
    }

    public Activity(String id, String userName, String action, String timeLabel, String color) {
        this.id = id;
        this.userName = userName;
        this.action = action;
        this.timeLabel = timeLabel;
        this.color = color;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(String timeLabel) {
        this.timeLabel = timeLabel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
