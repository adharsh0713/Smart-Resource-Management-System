package src;

import src.controller.ResourceManager;
import src.controller.TaskScheduler;
import src.view.MainDashboard;
import src.util.LoggerUtil;

import javax.swing.*;

/**
 * Entry point for the Smart Resource Management System.
 * It initializes controllers, loads resources, and launches the GUI dashboard.
 */
public class Main {

    public static void main(String[] args) {

        // Initialize system components
        LoggerUtil.log("Initializing Smart Resource Management System...");

        ResourceManager resourceManager = new ResourceManager();
        TaskScheduler taskScheduler = new TaskScheduler();

        // Load existing data if available
        resourceManager.loadResources();

        // Launch GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                new MainDashboard(resourceManager, taskScheduler).setVisible(true);
                LoggerUtil.log("Dashboard launched successfully.");
            } catch (Exception e) {
                LoggerUtil.error("Error launching dashboard: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
