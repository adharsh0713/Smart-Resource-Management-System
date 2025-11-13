package src.view;

// File: view/ResourceForm.java
// Description: Swing form to add or update employees and equipment

import src.controller.ResourceManager;
import src.util.LoggerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourceForm extends JFrame {

    private final ResourceManager resourceManager;
    private final JTextField idField, nameField, roleOrTypeField;
    private final JComboBox<String> resourceTypeBox;
    private final JButton addButton, updateButton, clearButton;
    private final JTextArea outputArea;

    public ResourceForm(ResourceManager resourceManager) {
        super("Add / Update Resource");
        this.resourceManager = resourceManager;

        setSize(500, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Title ---
        JLabel title = new JLabel("Resource Management Form", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Resource ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Resource Type:"));
        resourceTypeBox = new JComboBox<>(new String[]{"Employee", "Equipment"});
        inputPanel.add(resourceTypeBox);

        inputPanel.add(new JLabel("Role / Type:"));
        roleOrTypeField = new JTextField();
        inputPanel.add(roleOrTypeField);

        add(inputPanel, BorderLayout.CENTER);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        addButton = new JButton("Add Resource");
        updateButton = new JButton("Update Resource");
        clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // --- Output Panel ---
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.EAST);

        // --- Event Handling ---
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddResource();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateResource();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }

    private void handleAddResource() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String roleOrType = roleOrTypeField.getText().trim();
            String type = (String) resourceTypeBox.getSelectedItem();

            if (name.isEmpty() || roleOrType.isEmpty()) {
                showMessage("⚠️ Please fill all fields properly!");
                return;
            }

            if (type.equals("Employee")) {
                resourceManager.addEmployee(name, roleOrType);
                outputArea.append("✅ Added Employee: " + name + "\n");
            } else {
                resourceManager.addEquipment(name, roleOrType);
                outputArea.append("✅ Added Equipment: " + name + "\n");
            }

            LoggerUtil.info("Added new " + type + ": " + name);
        } catch (NumberFormatException ex) {
            showMessage("⚠️ Invalid ID format!");
        }
    }

    private void handleUpdateResource() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String roleOrType = roleOrTypeField.getText().trim();
            String type = (String) resourceTypeBox.getSelectedItem();

            if (name.isEmpty() || roleOrType.isEmpty()) {
                showMessage("⚠️ Please fill all fields properly!");
                return;
            }

            boolean updated = resourceManager.updateResource(id, name, roleOrType, type);
            if (updated) {
                showMessage("✅ Resource updated successfully!");
                LoggerUtil.info("Updated " + type + " with ID " + id);
            } else {
                showMessage("⚠️ Resource not found!");
            }

        } catch (NumberFormatException ex) {
            showMessage("⚠️ Invalid ID format!");
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        roleOrTypeField.setText("");
        outputArea.setText("");
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ResourceForm form = new ResourceForm(new ResourceManager());
            form.setVisible(true);
        });
    }
}

