package com.smartresourcemanagement.model;

public class HumanResource extends Resource {

    private String role;
    private String skillLevel;

    public HumanResource(String id, String name, String role, String skillLevel) {
        super(id, name);
        this.role = role;
        this.skillLevel = skillLevel;
    }

    public String getRole() { return role; }
    public String getSkillLevel() { return skillLevel; }

    public void setRole(String role) { this.role = role; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }

    @Override
    public String getResourceDetails() {
        return "HumanResource: ID=" + getId() +
               ", Name=" + getName() +
               ", Role=" + role +
               ", SkillLevel=" + skillLevel +
               ", CreatedOn=" + getCreatedOn();
    }
}
