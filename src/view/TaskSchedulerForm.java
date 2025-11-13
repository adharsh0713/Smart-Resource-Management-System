package src.view;

import src.controller.TaskScheduler;
import src.main.model.Task;
import src.util.TimeUtil;
import src.main.exceptions.InvalidResourceDataException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * TaskSchedulerForm provides a GUI for creating and scheduling tasks.
 * It allows the user to input a task name, assign a resource, and set a deadline.
 */
public class TaskSchedulerForm extends JFrame {

    private final JTextField taskNameField;
    private final JTextField resourceField;
    private final JTextField deadlineField;
    private final JButton scheduleButton;
    private final TaskScheduler taskScheduler;

    public TaskSchedulerForm(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;

        setTitle("Schedule New Task");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel taskLabel = new JLabel("Task Name:");
        taskNameField = new JTextField();

        JLabel resourceLabel = new JLabel("Assigned Resource:");
        resourceField = new JTextField();

        JLabel deadlineLabel = new JLabel("Deadline (yyyy-MM-dd HH:mm):");
        deadlineField = new JTextField();

        scheduleButton = new JButton("Schedule Task");

        add(taskLabel);
        add(taskNameField);
        add(resourceLabel);
        add(resourceField);
        add(deadlineLabel);
        add(deadlineField);
        add(new JLabel()); // Empty space
        add(scheduleButton);

        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTaskSchedule();
            }
        });
    }

    /**
     * Handles scheduling logic when user clicks "Schedule Task"
     */
    private void handleTaskSchedule() {
        String name = taskNameField.getText().trim();
        String resource = resourceField.getText().trim();
        String deadlineText = deadlineField.getText().trim();

        try {
            if (name.isEmpty() || resource.isEmpty() || deadlineText.isEmpty()) {
                throw new InvalidResourceDataException("All fields must be filled.");
            }

            LocalDateTime deadline = TimeUtil.parseDateTime(deadlineText);
            Task newTask = new Task(name, resource, deadline);
            taskScheduler.addTask(newTask);

            JOptionPane.showMessageDialog(this, "Task scheduled successfully!");
            clearForm();

        } catch (InvalidResourceDataException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Data", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error scheduling task: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        taskNameField.setText("");
        resourceField.setText("");
        deadlineField.setText("");
    }
}

