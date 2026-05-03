package com.library.app.ui;

import javax.swing.*;
import com.library.app.models.LibraryItem;
import com.library.app.services.ItemManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LocateFrame extends JFrame {
    public LocateFrame(ItemManager manager) {
        setTitle("Locate Library Item");
        setSize(380, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));
        container.setBackground(Color.WHITE);

        JLabel promptLabel = new JLabel("Enter Item ID to Locate:");
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        promptLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        promptLabel.setForeground(Color.DARK_GRAY);
        
        JTextField idSearchField = new JTextField();
        idSearchField.setMaximumSize(new Dimension(320, 34));
        idSearchField.setPreferredSize(new Dimension(320, 34));

        JButton searchBtn = new JButton("LOCATE");
        searchBtn.setBackground(new Color(255, 120, 40)); // Deep Orange
        searchBtn.setForeground(Color.DARK_GRAY);
        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchBtn.setMaximumSize(new Dimension(140, 40));
        searchBtn.setPreferredSize(new Dimension(140, 40));
        searchBtn.setFocusPainted(false);

        searchBtn.addActionListener(e -> {
            String searchID = idSearchField.getText().trim();
            LibraryItem found = manager.findItemById(searchID);

            if (found != null) {
                JOptionPane.showMessageDialog(this, 
                    "Item Found: " + found.getName() + "\nLocation: " + found.getLocation(), 
                    "Item Located", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Item ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        container.add(promptLabel);
        container.add(Box.createRigidArea(new Dimension(0, 12)));
        container.add(idSearchField);
        container.add(Box.createRigidArea(new Dimension(0, 18)));
        container.add(searchBtn);

        add(container);
        setVisible(true);
    }
}
