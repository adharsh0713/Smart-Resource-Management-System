package src.controller;

// File: controller/ResourceManager.java
// Description: Core controller class to manage all resource-related operations

import src.main.model.Resource;
import src.main.enums.ResourceType;
import src.main.exceptions.ResourceNotFoundException;
import src.main.exceptions.InvalidResourceDataException;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {

    private final List<Resource> resources;

    public ResourceManager() {
        // Load existing resources from file
        this.resources = FileHandler.loadResources();
    }

    // Add a new resource
    public void addResource(Resource resource) throws InvalidResourceDataException {
        if (resource == null || resource.getName() == null || resource.getName().isEmpty()) {
            throw new InvalidResourceDataException("Invalid resource data. Name cannot be empty!");
        }
        resources.add(resource);
        FileHandler.saveResources(resources);
        System.out.println("✅ Resource added successfully: " + resource.getName());
    }

    // Remove a resource by ID
    public void removeResource(int id) throws ResourceNotFoundException {
        Resource resource = findResourceById(id);
        resources.remove(resource);
        FileHandler.saveResources(resources);
        System.out.println("🗑️ Resource removed: " + resource.getName());
    }

    // Find a resource by ID
    public Resource findResourceById(int id) throws ResourceNotFoundException {
        return resources.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Resource with ID " + id + " not found."));
    }

    // Get all resources
    public List<Resource> getAllResources() {
        return new ArrayList<>(resources);
    }

    // Update a resource’s quantity
    public void updateResourceQuantity(int id, int newQuantity) throws ResourceNotFoundException {
        Resource resource = findResourceById(id);
        resource.setQuantity(newQuantity);
        FileHandler.saveResources(resources);
        System.out.println("✏️ Updated " + resource.getName() + " quantity to " + newQuantity);
    }

    // Filter resources by type
    public List<Resource> filterByType(ResourceType type) {
        List<Resource> filtered = new ArrayList<>();
        for (Resource r : resources) {
            if (r.getType() == type) filtered.add(r);
        }
        return filtered;
    }
}

