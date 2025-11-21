package com.smartresourcemanagement.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.smartresourcemanagement.model.Task;

public class TaskScheduler {

    private final List<Task> taskList;   // Stores scheduled tasks
    private Timer timer;

    public TaskScheduler() {
        this.taskList = new ArrayList<>();
        this.timer = new Timer(true);  // Daemon background thread
    }

    // Add a task to internal list
    public void addTask(Task task) {
        taskList.add(task);
    }

    // Schedule repeating task
    public void scheduleTask(long delay, long period, Runnable task) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay, period);
    }

    // Schedule one-time task
    public void scheduleOneTime(long delay, Runnable task) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay);
    }

    public void stopScheduler() {
        timer.cancel();
    }

    // Get all tasks (READ-ONLY COPY)
    public Collection<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }
}
