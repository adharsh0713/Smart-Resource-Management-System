package com.smartresourcemanagement.view;

import com.smartresourcemanagement.model.Resource;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.util.Collection;

public class ResourceListView extends Frame {

    public ResourceListView(Collection<Resource> resources) {

        setTitle("All Resources");
        setSize(400, 300);
        setLayout(new BorderLayout());

        TextArea area = new TextArea();
        area.setEditable(false);

        for (Resource r : resources) {
            area.append(r.toString() + "\n");
        }

        add(area, BorderLayout.CENTER);
    }
}
