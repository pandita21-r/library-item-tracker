package com.library.app.ui;

import javax.swing.*;
import com.library.app.services.ItemManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DeleteFrame extends JFrame {
    public DeleteFrame(ItemManager manager) {
        setTitle("Delete Library Item");
        setSize(380, 270);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));
        container.setBackground(Color.WHITE);

        JLabel promptLabel = new JLabel("Enter Item ID to DELETE:");
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        promptLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        promptLabel.setForeground(Color.DARK_GRAY);
        
        JTextField idField = new JTextField();
        idField.setMaximumSize(new Dimension(320, 34));
        idField.setPreferredSize(new Dimension(320, 34));

        JButton deleteBtn = new JButton("CONFIRM DELETE");
        deleteBtn.setBackground(new Color(255, 50, 50)); // Red for warning/deletion
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteBtn.setMaximumSize(new Dimension(160, 40));
        deleteBtn.setPreferredSize(new Dimension(160, 40));
        deleteBtn.setFocusPainted(false);

        deleteBtn.addActionListener(e -> {
            String id = idField.getText();
            
            // Ask for confirmation before deleting
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete Item ID: " + id + "?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean removed = manager.deleteItemById(id);
                if (removed) {
                    JOptionPane.showMessageDialog(this, "Item successfully removed.");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Item ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        container.add(promptLabel);
        container.add(Box.createRigidArea(new Dimension(0, 12)));
        container.add(idField);
        container.add(Box.createRigidArea(new Dimension(0, 18)));
        container.add(deleteBtn);

        add(container);
        setVisible(true);
    }
}

