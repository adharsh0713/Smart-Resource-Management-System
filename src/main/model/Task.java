package src.main.model;

// File: Task.java
// Description: Represents a task with resource usage details

public class Task {
    private int id;
    private String name;
    private String resourceType;
    private int quantity;
    private boolean isCompleted;

    // Constructor
    public Task(int id, String name, String resourceType, int quantity) {
        this.id = id;
        this.name = name;
        this.resourceType = resourceType;
        this.quantity = quantity;
        this.isCompleted = false; // default
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Display task details
    public void displayTask() {
        System.out.println("Task ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Resource Type: " + resourceType);
        System.out.println("Quantity: " + quantity);
        System.out.println("Completed: " + (isCompleted ? "Yes" : "No"));
        System.out.println("---------------------------------------");
    }

    // Mark task as complete
    public void completeTask() {
        this.isCompleted = true;
        System.out.println("Task '" + name + "' marked as completed!");
    }
}

