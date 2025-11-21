package com.smartresourcemanagement.view;

import com.smartresourcemanagement.controller.ResourceManager;
import com.smartresourcemanagement.enums.ResourceType;
import com.smartresourcemanagement.model.DigitalResource;
import com.smartresourcemanagement.model.HumanResource;
import com.smartresourcemanagement.model.PhysicalResource;
import com.smartresourcemanagement.model.Resource;
import com.smartresourcemanagement.exception.*;

import java.util.Scanner;

public class ConsoleUI {

    private ResourceManager manager;
    private Scanner scanner;

    public ConsoleUI(ResourceManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n==== RESOURCE MANAGEMENT CONSOLE ====");
            System.out.println("1. Add Resource");
            System.out.println("2. View All Resources");
            System.out.println("3. Search Resource");
            System.out.println("4. Remove Resource");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear

            switch (choice) {
                case 1 -> addResource();
                case 2 -> viewAll();
                case 3 -> search();
                case 4 -> remove();
                case 5 -> { 
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addResource() {
    try {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.println("Select Type: 1.HUMAN 2.DIGITAL 3.PHYSICAL");
        int t = Integer.parseInt(scanner.nextLine());

        ResourceType type =
                switch (t) {
                    case 1 -> ResourceType.HUMAN;
                    case 2 -> ResourceType.DIGITAL;
                    case 3 -> ResourceType.PHYSICAL;
                    default -> throw new InvalidResourceDataException("Invalid type chosen");
                };

        Resource resource;

        if (type == ResourceType.HUMAN) {
            // Human specific fields
            System.out.print("Enter Skill: ");
            String skill = scanner.nextLine();

            System.out.print("Enter Skill Level (Beginner/Intermediate/Expert): ");
            String skillLevel = scanner.nextLine();

            resource = new HumanResource(id, name, skill, skillLevel);

        } else if (type == ResourceType.DIGITAL) {
            // Digital specific fields
            System.out.print("Enter License Key: ");
            String licenseKey = scanner.nextLine();

            System.out.print("Enter Version: ");
            String version = scanner.nextLine();

            resource = new DigitalResource(id, name, licenseKey, version);

        } else {
            // Physical specific fields
            System.out.print("Enter Location: ");
            String location = scanner.nextLine();

            System.out.print("Enter Condition (New/Good/Fair/Damaged): ");
            String condition = scanner.nextLine();

            resource = new PhysicalResource(id, name, location, condition);
        }

        manager.addResource(resource);
        System.out.println("Resource added successfully!");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    private void viewAll() {
        if (manager.getAllResources().isEmpty()) {
            System.out.println("No resources found.");
            return;
        }
        for (Resource r : manager.getAllResources()) {
            System.out.println(r);
        }
    }

    private void search() {
        try {
            System.out.print("Enter Resource ID: ");
            String id = scanner.nextLine();

            Resource r = manager.getResourceById(id);
            System.out.println("FOUND: " + r);

        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void remove() {
        try {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            manager.removeResource(id);
            System.out.println("Removed.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
