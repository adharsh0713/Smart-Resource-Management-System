package src.main.model;

// Equipment class extending Resource
public class Equipment extends Resource {
    private String type;   // Example: Projector, Laptop, Machine

    public Equipment(int id, String name, String type) {
        super(id, name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void displayDetails() {
        System.out.println("Equipment Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Type: " + type);
        System.out.println("Available: " + (isAvailable() ? "Yes" : "No"));
        System.out.println("---------------------------------");
    }
}

