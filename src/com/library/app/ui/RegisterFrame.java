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
        setSize(400, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));
        container.setBackground(Color.WHITE);

        // Header - Using the Orange theme
        JLabel header = new JLabel("ITEM REGISTRATION", SwingConstants.CENTER);
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
        JPanel formPanel = new JPanel(new GridLayout(10, 1, 8, 8));
        formPanel.setBackground(Color.WHITE);
        
        nameField = new JTextField();
        creatorField = new JTextField();
        idField = new JTextField();
        categoryField = new JTextField();
        locationField = new JTextField(); // New field for physical location
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

        // Save Button
        JButton saveBtn = new JButton("SAVE ITEM");
        saveBtn.setBackground(new Color(100, 220, 230)); // Cyan
        saveBtn.setForeground(Color.DARK_GRAY);
        saveBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveBtn.setMaximumSize(new Dimension(160, 40));
        saveBtn.setPreferredSize(new Dimension(160, 40));
        saveBtn.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
        
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

        container.add(headerPanel);
        container.add(Box.createRigidArea(new Dimension(0, 18)));
        container.add(formPanel);
        container.add(Box.createRigidArea(new Dimension(0, 22)));
        container.add(saveBtn);

        add(container);
        setVisible(true);
    }
}
