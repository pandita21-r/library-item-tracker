package com.library.app.ui;

import javax.swing.*;
import com.library.app.services.LoginService;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private LoginService loginService;

    public LoginFrame() {
        loginService = new LoginService();
        setTitle("Library Item Tracker - Login");
        setSize(400, 550); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Background of the frame
        getContentPane().setBackground(Color.WHITE);

        // Main container with BoxLayout (Vertical)
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(40, 30, 40, 30));

        JLabel headerLabel = new JLabel("LIBRARY ITEM TRACKER", SwingConstants.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(100, 220, 230));
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setMaximumSize(new Dimension(350, 100)); // Controlled height, full width
        headerLabel.setPreferredSize(new Dimension(350, 100));

        // 2. "LOGIN" label
        JLabel loginText = new JLabel("LOGIN");
        loginText.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginText.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 3. Input Panel (Orange Box) - This is where the color occupies more space
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout()); 
        inputPanel.setBackground(new Color(255, 180, 80));
        inputPanel.setMaximumSize(new Dimension(350, 180)); 
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        userField = new JTextField();
        passField = new JPasswordField();
        
        userField.setPreferredSize(new Dimension(0, 35));
        passField.setPreferredSize(new Dimension(0, 35));

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("USERNAME:"), gbc);
        gbc.gridy = 1;
        inputPanel.add(userField, gbc);
        gbc.gridy = 2;
        inputPanel.add(new JLabel("PASSWORD:"), gbc);
        gbc.gridy = 3;
        inputPanel.add(passField, gbc);

        // 4. Login Button
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setPreferredSize(new Dimension(100, 40));
        loginBtn.setFocusPainted(false);
        
        loginBtn.addActionListener(e -> handleLogin());

        // Adding components to the container with spacers
        container.add(headerLabel);
        container.add(Box.createRigidArea(new Dimension(0, 40))); // Big gap below header
        container.add(loginText);
        container.add(Box.createRigidArea(new Dimension(0, 10))); // Small gap below LOGIN text
        container.add(inputPanel);
        container.add(Box.createRigidArea(new Dimension(0, 30))); // Gap below orange box
        container.add(loginBtn);
        
        // Footer (Brand text)
        container.add(Box.createVerticalGlue()); // Pushes footer to the bottom
        JLabel footer = new JLabel("BY RAYMARK PANDITA");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 10));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(footer);

        add(container);
        setVisible(true);
    }

    private void handleLogin() {
        String username = userField.getText();
        String password = new String(passField.getPassword());
        if (loginService.authenticate(username, password)) {
            new DashboardFrame();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }
}
