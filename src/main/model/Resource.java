package src.main.model;

public abstract class Resource {
    private int id;
    private String name;
    private boolean available;

    public Resource(int id, String name) {
        this.id = id;
        this.name = name;
        this.available = true; // default: available
    }

    // Abstract method (forces subclasses to implement)
    public abstract void displayDetails();

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Common method for all resources
    public void toggleAvailability() {
        this.available = !this.available;
    }

    @Override
    public String toString() {
        return "Resource [ID=" + id + ", Name=" + name + ", Available=" + available + "]";
    }
}
