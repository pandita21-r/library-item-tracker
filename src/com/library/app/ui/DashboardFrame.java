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
        setSize(380, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header
        JLabel headerLabel = new JLabel("LIBRARY ITEM TRACKER", SwingConstants.CENTER);
        headerLabel.setOpaque(false);
        headerLabel.setForeground(new Color(40, 40, 40));
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel iconLabel = new JLabel(new BookIcon(34, 34, new Color(255, 180, 80), new Color(255, 240, 210), new Color(100, 220, 230)));

        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(340, 56));
        headerPanel.add(iconLabel, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 210)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Buttons
        JButton regBtn = createMenuButton("REGISTER AN ITEM", new Color(255, 180, 80));
        JButton locBtn = createMenuButton("LOCATE ITEM", new Color(255, 120, 40));
        JButton delBtn = createMenuButton("DELETE ITEM", new Color(100, 220, 230));
        JButton updBtn = createMenuButton("UPDATE ITEM", new Color(150, 255, 150));

        // Listeners
        regBtn.addActionListener(e -> new RegisterFrame(this.itemManager));
        locBtn.addActionListener(e -> new LocateFrame(this.itemManager));
        delBtn.addActionListener(e -> new DeleteFrame(this.itemManager));
        updBtn.addActionListener(e -> new UpdateFrame(this.itemManager));

        container.add(headerPanel);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(regBtn);
        container.add(Box.createRigidArea(new Dimension(0, 14)));
        container.add(locBtn);
        container.add(Box.createRigidArea(new Dimension(0, 14)));
        container.add(delBtn);
        container.add(Box.createRigidArea(new Dimension(0, 14)));
        container.add(updBtn);
        container.add(Box.createVerticalGlue());
        JLabel footer = new JLabel("BY RAYMARK PANDITA");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 10));
        footer.setForeground(Color.GRAY);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        container.add(footer);

        add(container);
        setVisible(true);
    }

    private JButton createMenuButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(bgColor);
        btn.setForeground(Color.DARK_GRAY);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(340, 72));
        btn.setPreferredSize(new Dimension(340, 72));
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        return btn;
    }
}
