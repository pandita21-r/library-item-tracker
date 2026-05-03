package com.library.app.ui;

import javax.swing.*;
import com.library.app.models.LibraryItem;
import com.library.app.services.ItemManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UpdateFrame extends JFrame {
    private JTextField nameField, creatorField, idField, categoryField, locationField;
    private JButton updateBtn;
    private ItemManager itemManager;

    public UpdateFrame(ItemManager manager) {
        this.itemManager = manager;

        setTitle("Update Library Item");
        setSize(400, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));
        container.setBackground(Color.WHITE);

        // Header
        JLabel header = new JLabel("UPDATE ITEM", SwingConstants.CENTER);
        header.setOpaque(false);
        header.setForeground(new Color(40, 40, 40));
        header.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel iconLabel = new JLabel(new BookIcon(30, 30, new Color(255, 180, 80), new Color(255, 245, 220), new Color(100, 220, 230)));
        JPanel headerPanel = new JPanel(new BorderLayout(8, 0));
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(340, 54));
        headerPanel.add(iconLabel, BorderLayout.WEST);
        headerPanel.add(header, BorderLayout.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // ID Input Panel
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        idPanel.setBackground(Color.WHITE);
        JLabel idLabel = new JLabel("Enter Item ID to Update:");
        idLabel.setForeground(Color.DARK_GRAY);
        idPanel.add(idLabel);
        JTextField searchIdField = new JTextField();
        searchIdField.setPreferredSize(new Dimension(160, 32));
        idPanel.add(searchIdField);

        JButton loadBtn = new JButton("LOAD ITEM");
        loadBtn.setBackground(new Color(100, 220, 230));
        loadBtn.setForeground(Color.DARK_GRAY);
        loadBtn.setFocusPainted(false);
        loadBtn.setPreferredSize(new Dimension(100, 32));
        loadBtn.addActionListener(e -> {
            String searchId = searchIdField.getText().trim();
            if (searchId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an Item ID before loading.", "Input Required", JOptionPane.WARNING_MESSAGE);
            } else {
                loadItem(searchId);
            }
        });

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(10, 1, 8, 8));
        formPanel.setBackground(Color.WHITE);

        nameField = new JTextField();
        creatorField = new JTextField();
        idField = new JTextField();
        idField.setEditable(false); // ID should not be editable
        categoryField = new JTextField();
        locationField = new JTextField();
        nameField.setPreferredSize(new Dimension(0, 34));
        creatorField.setPreferredSize(new Dimension(0, 34));
        idField.setPreferredSize(new Dimension(0, 34));
        categoryField.setPreferredSize(new Dimension(0, 34));
        locationField.setPreferredSize(new Dimension(0, 34));

        formPanel.add(new JLabel("Item Name / Title:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Creator (Author/Director/Brand):"));
        formPanel.add(creatorField);
        formPanel.add(new JLabel("ID / ISBN / Serial Number:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Category (Book/Gadget/Media):"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Physical Location (e.g., Aisle 2, Shelf 3):"));
        formPanel.add(locationField);

        // Update Button
        updateBtn = new JButton("UPDATE ITEM");
        updateBtn.setBackground(new Color(100, 220, 230));
        updateBtn.setForeground(Color.DARK_GRAY);
        updateBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateBtn.setMaximumSize(new Dimension(160, 40));
        updateBtn.setPreferredSize(new Dimension(160, 40));
        updateBtn.setEnabled(false);
        updateBtn.setFocusPainted(false);

        updateBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String creator = creatorField.getText().trim();
            String cat = categoryField.getText().trim();
            String loc = locationField.getText().trim();

            if (!id.isEmpty() && !name.isEmpty() && !loc.isEmpty()) {
                boolean updated = itemManager.updateItem(id, name, creator, cat, loc);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "Item Updated Successfully!");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Item ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Name and Location are required!");
            }
        });

        container.add(headerPanel);
        container.add(Box.createRigidArea(new Dimension(0, 16)));
        container.add(idPanel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        loadBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(loadBtn);
        container.add(Box.createRigidArea(new Dimension(0, 18)));
        container.add(formPanel);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(updateBtn);

        add(container);
        setVisible(true);
    }

    private void loadItem(String id) {
        LibraryItem item = itemManager.findItemById(id);
        if (item != null) {
            nameField.setText(item.getName());
            creatorField.setText(item.getCreator());
            idField.setText(item.getId());
            categoryField.setText(item.getCategory());
            locationField.setText(item.getLocation());
            updateBtn.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Item loaded. You can now edit and update.", "Item Loaded", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Item ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}