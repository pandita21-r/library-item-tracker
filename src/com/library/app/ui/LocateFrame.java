package com.library.app.ui;

import javax.swing.*;
import com.library.app.models.LibraryItem;
import com.library.app.services.ItemManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LocateFrame extends JFrame {
    public LocateFrame(ItemManager manager) {
        setTitle("Locate Library Item");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(30, 30, 30, 30));
        container.setBackground(Color.WHITE);

        JLabel promptLabel = new JLabel("Enter Item ID to Locate:");
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextField idSearchField = new JTextField();
        idSearchField.setMaximumSize(new Dimension(300, 40));

        JButton searchBtn = new JButton("LOCATE");
        searchBtn.setBackground(new Color(255, 120, 40)); // Deep Orange
        searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchBtn.addActionListener(e -> {
            String searchID = idSearchField.getText();
            // Use manager to find the item
            LibraryItem found = null;
            for (LibraryItem item : manager.getInventory()) {
                if (item.getId().equalsIgnoreCase(searchID)) {
                    found = item;
                    break;
                }
            }

            if (found != null) {
                JOptionPane.showMessageDialog(this, 
                    "Item Found: " + found.getName() + "\nLocation: " + found.getLocation(), 
                    "Item Located", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Item ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        container.add(promptLabel);
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(idSearchField);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(searchBtn);

        add(container);
        setVisible(true);
    }
}
