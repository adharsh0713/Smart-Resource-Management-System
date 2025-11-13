package src.main.exceptions;

// File: ResourceNotFoundException.java
// Description: Custom exception for handling missing resources

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

