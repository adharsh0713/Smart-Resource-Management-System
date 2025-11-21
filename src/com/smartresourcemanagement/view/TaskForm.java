package com.smartresourcemanagement.view;

import com.smartresourcemanagement.controller.ResourceManager;
import com.smartresourcemanagement.controller.TaskScheduler;
// import com.smartresourcemanagement.exception.InvalidTaskException;
import com.smartresourcemanagement.model.Task;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TaskForm extends Frame {

    private TextField idField;
    private TextField resIdField;
    private TextField descField;
    private TextField durationField;

    private TaskScheduler scheduler;

    public TaskForm(ResourceManager manager, TaskScheduler scheduler) {
        this.scheduler = scheduler;

        setTitle("Add Task");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2));

        idField = new TextField();
        resIdField = new TextField();
        descField = new TextField();
        durationField = new TextField();

        add(new Label("Task ID:"));
        add(idField);

        add(new Label("Resource ID:"));
        add(resIdField);

        add(new Label("Description:"));
        add(descField);

        add(new Label("Duration:"));
        add(durationField);

        Button btn = new Button("Submit");
        add(btn);

        btn.addActionListener(e -> submitTask());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
    }

    private void submitTask() {
        try {
            String id = idField.getText();
            String resId = resIdField.getText();
            String desc = descField.getText();
            int dur = Integer.parseInt(durationField.getText());

            Task t = new Task(id, resId, desc, dur);
            scheduler.addTask(t);

            System.out.println("Task Added (via GUI)");

            setVisible(false);

        } catch (Exception ex) {
            System.out.println("Unexpected Error: " + ex.getMessage());
        }
    }
}
