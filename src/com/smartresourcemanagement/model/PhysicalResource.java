package com.smartresourcemanagement.model;

public class PhysicalResource extends Resource {

    private String location;
    private String condition;

    public PhysicalResource(String id, String name, String location, String condition) {
        super(id, name);
        this.location = location;
        this.condition = condition;
    }

    public String getLocation() { return location; }
    public String getCondition() { return condition; }

    public void setLocation(String location) { this.location = location; }
    public void setCondition(String condition) { this.condition = condition; }

    @Override
    public String getResourceDetails() {
        return "PhysicalResource: ID=" + getId() +
               ", Name=" + getName() +
               ", Location=" + location +
               ", Condition=" + condition +
               ", CreatedOn=" + getCreatedOn();
    }
}
