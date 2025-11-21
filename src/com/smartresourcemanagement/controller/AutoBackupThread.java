package com.smartresourcemanagement.controller;

import com.smartresourcemanagement.model.Resource;
import java.util.Collection;

public class AutoBackupThread extends Thread {

    private final FileHandler fileHandler;
    private final String backupFileName;
    private final Collection<Resource> resources;
    private boolean running = true;
    private long intervalMillis;

    public AutoBackupThread(String backupFileName, 
                            Collection<Resource> resources, 
                            long intervalMillis) {
        this.fileHandler = new FileHandler();
        this.backupFileName = backupFileName;
        this.resources = resources;
        this.intervalMillis = intervalMillis;
    }

    @Override
    public void run() {
        System.out.println("[AutoBackupThread] Started automatic backup every " 
                           + intervalMillis + " ms");

        while (running) {
            try {
                Thread.sleep(intervalMillis);
                fileHandler.saveToFile(backupFileName, resources);
                System.out.println("[AutoBackupThread] Backup completed.");
            } catch (InterruptedException e) {
                System.out.println("[AutoBackupThread] Backup interrupted: " + e.getMessage());
            }
        }

        System.out.println("[AutoBackupThread] Stopped.");
    }

    // Graceful shutdown
    public void stopBackup() {
        running = false;
        this.interrupt();
    }
}
