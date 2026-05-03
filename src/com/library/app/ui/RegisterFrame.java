package com.library.app.ui;

import javax.swing.*;
import com.library.app.models.LibraryItem;
import com.library.app.services.ItemManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField nameField, creatorField, idField, categoryField, locationField;
    private ItemManager itemManager;

    public RegisterFrame(ItemManager manager) {
        this.itemManager = manager;
        
        setTitle("Register New Library Item");
        setSize(400, 600); // Increased height to fit the new field
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(30, 30, 30, 30));
        container.setBackground(Color.WHITE);

        // Header - Using the Orange theme
        JLabel header = new JLabel("ITEM REGISTRATION", SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(new Color(255, 180, 80)); 
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setMaximumSize(new Dimension(350, 60));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Form Panel with the new Location field
        JPanel formPanel = new JPanel(new GridLayout(10, 1, 5, 2)); // Updated to 10 rows
        formPanel.setBackground(Color.WHITE);
        
        nameField = new JTextField();
        creatorField = new JTextField();
        idField = new JTextField();
        categoryField = new JTextField();
        locationField = new JTextField(); // New field for physical location

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

        // Save Button
        JButton saveBtn = new JButton("SAVE ITEM");
        saveBtn.setBackground(new Color(100, 220, 230)); // Cyan
        saveBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveBtn.setMaximumSize(new Dimension(150, 40));
        
        saveBtn.addActionListener(e -> {
            String name = nameField.getText();
            String creator = creatorField.getText();
            String id = idField.getText();
            String cat = categoryField.getText();
            String loc = locationField.getText();

            if (!name.isEmpty() && !id.isEmpty() && !loc.isEmpty()) {
                // Now passing 5 arguments to match the updated LibraryItem constructor
                LibraryItem newItem = new LibraryItem(name, creator, id, cat, loc);
                itemManager.registerItem(newItem);
                
                JOptionPane.showMessageDialog(this, "Item Saved Successfully!");
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Name, ID, and Location are required!");
            }
        });

        container.add(header);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(formPanel);
        container.add(Box.createRigidArea(new Dimension(0, 25)));
        container.add(saveBtn);

        add(container);
        setVisible(true);
    }
}
