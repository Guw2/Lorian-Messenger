package com.lorian.frontend_service.security.authentication.DTOs;

public class FormDTO {
    private String username;
    private String password;

    public FormDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
