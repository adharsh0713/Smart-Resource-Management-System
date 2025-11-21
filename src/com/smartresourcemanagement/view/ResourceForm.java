package com.smartresourcemanagement.view;

import com.smartresourcemanagement.controller.ResourceManager;
import com.smartresourcemanagement.enums.ResourceType;
import com.smartresourcemanagement.model.HumanResource;
import com.smartresourcemanagement.model.DigitalResource;
import com.smartresourcemanagement.model.PhysicalResource;
import com.smartresourcemanagement.model.Resource;
import com.smartresourcemanagement.exception.InvalidResourceDataException;
import com.smartresourcemanagement.exception.DuplicateResourceException;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ResourceForm extends Frame {

    private TextField idField;
    private TextField nameField;
    private Choice typeChoice;
    private ResourceManager manager;

    public ResourceForm(ResourceManager manager) {
        this.manager = manager;

        setTitle("Add Resource");
        setSize(350, 250);
        setLayout(new GridLayout(4, 2));

        Label idLbl = new Label("Resource ID:");
        Label nameLbl = new Label("Resource Name:");
        Label typeLbl = new Label("Type:");

        idField = new TextField();
        nameField = new TextField();
        typeChoice = new Choice();

        typeChoice.add("HUMAN");
        typeChoice.add("DIGITAL");
        typeChoice.add("PHYSICAL");

        Button submitBtn = new Button("Submit");

        add(idLbl);
        add(idField);
        add(nameLbl);
        add(nameField);
        add(typeLbl);
        add(typeChoice);
        add(submitBtn);

        submitBtn.addActionListener(e -> submitResource());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
    }

    private void submitResource() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String typeStr = typeChoice.getSelectedItem();

            ResourceType type = ResourceType.valueOf(typeStr);

            Resource resource = createResource(type, id, name);

            manager.addResource(resource);

            System.out.println("Resource Added Successfully!");
            setVisible(false);

        } catch (DuplicateResourceException | InvalidResourceDataException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Unexpected Error: " + ex.getMessage());
        }
    }

    private Resource createResource(ResourceType type, String id, String name) {

    switch (type) {
        case HUMAN:
            // Default role + skillLevel
            return new HumanResource(
                    id,
                    name,
                    "GENERAL",        // role
                    "BEGINNER"        // skill level
            );

        case DIGITAL:
            // Default license + version
            return new DigitalResource(
                    id,
                    name,
                    "LIC-DEFAULT",    // license key
                    "1.0"             // version
            );

        case PHYSICAL:
            // Default location + condition
            return new PhysicalResource(
                    id,
                    name,
                    "WAREHOUSE-A",    // location
                    "GOOD"            // condition
            );

        default:
            throw new IllegalArgumentException("Unknown Resource Type");
    }
}

}
