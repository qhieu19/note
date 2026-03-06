package com.devboard.model;

public class Activity {
    private String id;
    private String user;
    private String action;
    private String timeLabel;
    private String color; // hex or CSS variable

    public Activity() {
    }

    public Activity(String id, String user, String action, String timeLabel, String color) {
        this.id = id;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
