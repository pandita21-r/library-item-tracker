package com.library.app.services;

public class LoginService {
    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }
}

