package src.controller;

// File: controller/TaskScheduler.java
// Description: Handles scheduling and running background tasks using threads

import src.main.model.Task;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskScheduler implements Runnable {

    private final List<Task> taskList;
    private boolean running;

    public TaskScheduler() {
        this.taskList = new ArrayList<>();
        this.running = true;
    }

    // Add a task to the scheduler
    public synchronized void addTask(Task task) {
        taskList.add(task);
        System.out.println("🗓️ Task added: " + task.getDescription() + " at " + task.getScheduledTime());
    }

    // Remove a task by ID
    public synchronized void removeTask(int id) {
        taskList.removeIf(t -> t.getId() == id);
        System.out.println("🗑️ Task removed with ID: " + id);
    }

    // Start background monitoring thread
    @Override
    public void run() {
        System.out.println("⚙️ Task Scheduler started...");
        while (running) {
            synchronized (this) {
                LocalDateTime now = LocalDateTime.now();
                for (Task task : new ArrayList<>(taskList)) {
                    if (!task.isCompleted() && now.isAfter(task.getScheduledTime())) {
                        executeTask(task);
                        task.setCompleted(true);
                    }
                }
            }

            try {
                Thread.sleep(5000); // Check every 5 seconds
            } catch (InterruptedException e) {
                System.out.println("⏸️ Scheduler interrupted: " + e.getMessage());
            }
        }
    }

    // Execute a single task
    private void executeTask(Task task) {
        System.out.println("🚀 Executing Task: " + task.getDescription() + " at " + LocalDateTime.now());
        // (Future scope: integrate actual resource operations here)
    }

    // Stop scheduler gracefully
    public void stopScheduler() {
        this.running = false;
        System.out.println("🛑 Task Scheduler stopped.");
    }
}

