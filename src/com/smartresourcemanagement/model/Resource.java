package com.smartresourcemanagement.model;

import java.time.LocalDate;

public abstract class Resource {
    private String id;
    private String name;
    private LocalDate createdOn;

    public Resource(String id, String name) {
        this.id = id;
        this.name = name;
        this.createdOn = LocalDate.now();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public LocalDate getCreatedOn() { return createdOn; }

    // Setters
    public void setName(String name) { this.name = name; }

    // Polymorphic → overridden by subclasses
    public abstract String getResourceDetails();

    @Override
    public String toString() {
        return getResourceDetails();
    }
}
