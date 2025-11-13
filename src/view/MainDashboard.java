package src.view;

// File: view/MainDashboard.java
// Description: Swing-based dashboard for viewing and managing resources & tasks

import src.controller.ResourceManager;
import src.controller.TaskScheduler;
import src.main.model.Task;
import src.main.model.Resource;
import src.util.LoggerUtil;
import src.util.TimeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class MainDashboard extends JFrame {

    private final ResourceManager resourceManager;
    private final TaskScheduler taskScheduler;

    // GUI Components
    private final JTextArea displayArea;
    private final JButton addEmployeeBtn;
    private final JButton addEquipmentBtn;
    private final JButton scheduleTaskBtn;
    private final JButton showResourcesBtn;
    private final JButton showTasksBtn;

    public MainDashboard() {
        super("Smart Resource Management System");

        // Initialize backend managers
        resourceManager = new ResourceManager();
        taskScheduler = new TaskScheduler();

        // Start scheduler in background thread
        Thread schedulerThread = new Thread(taskScheduler);
        schedulerThread.start();

        // Setup layout
        setLayout(new BorderLayout());
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Buttons Panel ---
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        addEmployeeBtn = new JButton("Add Employee");
        addEquipmentBtn = new JButton("Add Equipment");
        scheduleTaskBtn = new JButton("Schedule Task");
        showResourcesBtn = new JButton("Show Resources");
        showTasksBtn = new JButton("Show Tasks");

        buttonPanel.add(addEmployeeBtn);
        buttonPanel.add(addEquipmentBtn);
        buttonPanel.add(scheduleTaskBtn);
        buttonPanel.add(showResourcesBtn);
        buttonPanel.add(showTasksBtn);

        add(buttonPanel, BorderLayout.NORTH);

        // --- Display Area ---
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // --- Event Handlers ---
        addEmployeeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Employee Name:");
                String role = JOptionPane.showInputDialog("Enter Role (e.g., Doctor, Teacher, Staff):");
                if (name != null && role != null) {
                    resourceManager.addEmployee(name, role);
                    LoggerUtil.info("Added new employee: " + name);
                    showMessage("✅ Employee added successfully!");
                }
            }
        });

        addEquipmentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Equipment Name:");
                String type = JOptionPane.showInputDialog("Enter Equipment Type (e.g., Projector, Laptop):");
                if (name != null && type != null) {
                    resourceManager.addEquipment(name, type);
                    LoggerUtil.info("Added new equipment: " + name);
                    showMessage("✅ Equipment added successfully!");
                }
            }
        });

        scheduleTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = JOptionPane.showInputDialog("Enter Task Name:");
                String resourceIdStr = JOptionPane.showInputDialog("Enter Resource ID to Assign:");
                if (taskName != null && resourceIdStr != null) {
                    try {
                        int resourceId = Integer.parseInt(resourceIdStr);
                        Resource resource = resourceManager.getResourceById(resourceId);
                        if (resource != null) {
                            Task task = new Task(resourceId, taskName, resource, LocalDateTime.now().plusSeconds(10));
                            taskScheduler.addTask(task);
                            LoggerUtil.info("Scheduled task: " + taskName + " for resource ID: " + resourceId);
                            showMessage("🗓️ Task scheduled successfully!");
                        } else {
                            showMessage("⚠️ Resource not found!");
                        }
                    } catch (NumberFormatException ex) {
                        showMessage("⚠️ Invalid ID input!");
                    }
                }
            }
        });

        showResourcesBtn.addActionListener(e -> {
            displayArea.setText(resourceManager.displayAllResources());
        });

        showTasksBtn.addActionListener(e -> {
            displayArea.setText(resourceManager.displayAllTasks());
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainDashboard dashboard = new MainDashboard();
            dashboard.setVisible(true);
            LoggerUtil.info("Smart Resource Management System started at " + TimeUtil.getCurrentTimestamp());
        });
    }
}

