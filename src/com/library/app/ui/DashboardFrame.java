package com.library.app.ui;

import javax.swing.*;
import com.library.app.services.ItemManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private ItemManager itemManager;

    public DashboardFrame() {
        this.itemManager = new ItemManager(); 

        setTitle("Library Dashboard");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header
        JLabel headerLabel = new JLabel("LIBRARY ITEM TRACKER", SwingConstants.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(100, 220, 230));
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setMaximumSize(new Dimension(350, 80));

        // Buttons
        JButton regBtn = createMenuButton("REGISTER AN ITEM", new Color(255, 180, 80));
        JButton locBtn = createMenuButton("LOCATE ITEM", new Color(255, 120, 40));
        JButton delBtn = createMenuButton("DELETE ITEM", new Color(100, 220, 230));

        // Listeners
        regBtn.addActionListener(e -> new RegisterFrame(this.itemManager));
        locBtn.addActionListener(e -> new LocateFrame(this.itemManager));
        delBtn.addActionListener(e -> new DeleteFrame(this.itemManager));

        container.add(headerLabel);
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        container.add(regBtn);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(locBtn);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(delBtn);

        container.add(Box.createVerticalGlue());
        JLabel footer = new JLabel("BY RAYMARK PANDITA");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 10));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(footer);

        add(container);
        setVisible(true);
    }

    private JButton createMenuButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(350, 100));
        btn.setPreferredSize(new Dimension(350, 100));
        return btn;
    }
}
