package com.smartresourcemanagement.view;

import com.smartresourcemanagement.controller.ResourceManager;
import com.smartresourcemanagement.controller.TaskScheduler;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUI extends Frame {
    public MainGUI(ResourceManager manager, TaskScheduler scheduler) {
        setTitle("Smart Resource Management - Dashboard");
        setSize(400, 350);
        setLayout(new FlowLayout());

        // Buttons
        Button addResBtn = new Button("Add Resource");
        Button viewResBtn = new Button("View All Resources");

        Button addTaskBtn = new Button("Add Task");
        Button viewTaskBtn = new Button("View Task Scheduler");

        Button exitBtn = new Button("Exit");

        // Add buttons to UI
        add(addResBtn);
        add(viewResBtn);
        add(addTaskBtn);
        add(viewTaskBtn);
        add(exitBtn);

        // Actions
        addResBtn.addActionListener(e -> new ResourceForm(manager).setVisible(true));

        viewResBtn.addActionListener(e -> new ResourceListView(manager.getAllResources()).setVisible(true));

        addTaskBtn.addActionListener(e -> new TaskForm(manager, scheduler).setVisible(true));

        viewTaskBtn.addActionListener(e -> new TaskListView(scheduler.getAllTasks()).setVisible(true));

        exitBtn.addActionListener(e -> System.exit(0));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
