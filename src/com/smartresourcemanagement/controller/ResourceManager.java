package com.smartresourcemanagement.controller;

import java.util.HashMap; // specific import
import java.util.ArrayList; // specific import
import java.util.List; // specific import
import java.util.Collection; // specific import
import java.util.Map; // specific import
import com.smartresourcemanagement.model.Resource; // specific import
import com.smartresourcemanagement.model.HumanResource; // specific import
import com.smartresourcemanagement.model.DigitalResource; // specific import
import com.smartresourcemanagement.model.PhysicalResource; // specific import
import com.smartresourcemanagement.enums.ResourceType; // specific import
import com.smartresourcemanagement.exception.ResourceNotFoundException; // specific import
import com.smartresourcemanagement.exception.InvalidResourceDataException; // specific import
import com.smartresourcemanagement.exception.DuplicateResourceException; // specific import

public class ResourceManager {

    private Map<String, Resource> resourceMap; // resource storage

    public ResourceManager() {
        resourceMap = new HashMap<>(); // initialize map
    }

    // Create resource
    public void addResource(Resource resource) throws DuplicateResourceException, InvalidResourceDataException {
        if (resource == null) {
            throw new InvalidResourceDataException("Resource cannot be null");
        }
        if (resourceMap.containsKey(resource.getId())) {
            throw new DuplicateResourceException("Resource with ID " + resource.getId() + " already exists");
        }
        resourceMap.put(resource.getId(), resource);
    }

    // Read by ID
    public Resource getResourceById(String id) throws ResourceNotFoundException {
        if (!resourceMap.containsKey(id)) {
            throw new ResourceNotFoundException("Resource with ID " + id + " not found");
        }
        return resourceMap.get(id);
    }

    // Update existing resource
    public void updateResource(String id, Resource updatedResource) throws ResourceNotFoundException {
        if (!resourceMap.containsKey(id)) {
            throw new ResourceNotFoundException("Cannot update. ID " + id + " does not exist");
        }
        resourceMap.put(id, updatedResource);
    }

    // Delete resource
    public void removeResource(String id) throws ResourceNotFoundException {
        if (!resourceMap.containsKey(id)) {
            throw new ResourceNotFoundException("Cannot delete. ID " + id + " does not exist");
        }
        resourceMap.remove(id);
    }

    // Filter by resource type using polymorphism
    public List<Resource> getResourcesByType(ResourceType type) {
        List<Resource> results = new ArrayList<>();

        for (Resource r : resourceMap.values()) {
            if (type == ResourceType.HUMAN && r instanceof HumanResource) {
                results.add(r);
            } else if (type == ResourceType.DIGITAL && r instanceof DigitalResource) {
                results.add(r);
            } else if (type == ResourceType.PHYSICAL && r instanceof PhysicalResource) {
                results.add(r);
            }
        }
        return results;
    }

    // Return all resources
    public Collection<Resource> getAllResources() {
        return resourceMap.values();
    }
}
