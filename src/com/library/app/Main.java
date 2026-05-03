package com.library.app;

import javax.swing.SwingUtilities;
import com.library.app.ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
