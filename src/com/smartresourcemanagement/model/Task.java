package com.smartresourcemanagement.model;

public class Task {

    private String taskId;
    private String resourceId;
    private String description;
    private int duration; // duration in hours or minutes

    public Task(String taskId, String resourceId, String description, int duration) {
        this.taskId = taskId;
        this.resourceId = resourceId;
        this.description = description;
        this.duration = duration;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Task {" +
                "ID = '" + taskId + '\'' +
                ", Resource = '" + resourceId + '\'' +
                ", Description = '" + description + '\'' +
                ", Duration = " + duration +
                '}';
    }
}
