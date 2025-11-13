package src.main.model;

// Employee inherits common fields from Resource
public class Employee extends Resource {
    private String role;   // Example: Doctor, Teacher, Manager

    public Employee(int id, String name, String role) {
        super(id, name);  // calling the Resource constructor
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void displayDetails() {
        System.out.println("Employee Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Role: " + role);
        System.out.println("Available: " + (isAvailable() ? "Yes" : "No"));
        System.out.println("---------------------------------");
    }
}
