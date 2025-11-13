package src.controller;

// File: controller/FileHandler.java
// Description: Handles saving and loading resource data to and from files

import src.main.model.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "data/resources.ser";

    // Save resources to a file (Serialization)
    public static void saveResources(List<Resource> resources) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(resources);
            System.out.println("✅ Resources saved successfully!");
        } catch (IOException e) {
            System.err.println("❌ Error saving resources: " + e.getMessage());
        }
    }

    // Load resources from a file (Deserialization)
    @SuppressWarnings("unchecked")
    public static List<Resource> loadResources() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("ℹ️ No previous resource data found. Starting fresh.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Resource>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error loading resources: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

