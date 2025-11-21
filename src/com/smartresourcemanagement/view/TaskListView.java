package com.smartresourcemanagement.view;

import com.smartresourcemanagement.model.Task;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

public class TaskListView extends Frame {

    public TaskListView(Collection<Task> tasks) {

        super("All Tasks");   // sets title
        setSize(400, 300);
        setLayout(new BorderLayout());

        TextArea area = new TextArea();
        area.setEditable(false);

        for (Task t : tasks) {
            area.append(t.toString() + "\n");
        }

        add(area, BorderLayout.CENTER);

        // Essential: window listener for close button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // properly closes the window
            }
        });

        setVisible(true);  // make sure to show window after adding components
    }
}
