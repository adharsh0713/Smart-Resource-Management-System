package com.smartresourcemanagement.controller;

import java.io.*;
import java.util.Collection;

import com.smartresourcemanagement.model.Resource;

public class FileHandler {

    // Save all resources to a file
    public void saveToFile(String filename, Collection<Resource> resources) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            
            for (Resource r : resources) {
                writer.write(r.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read file (simple implementation for demo)
    public void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            
            String line;
            System.out.println("---- Saved Resources ----");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
