package com.example.projectnews.model;

public class Account {
    private String username;
    private String password;
    private String email;
    private String status;
    private String role;
    private byte [] img;

    public Account(String username, String password, String email, String status, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
    }
    public Account(String username, String email, String status, String role) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public Account(String username, String email, String status, String role, byte[] img) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.role = role;
        this.img = img;
    }

    public Account(String username, String email, byte [] img) {
        this.username = username;
        this.email = email;
        this.img=img;
    }
    public Account(String username, String email) {
        this.username = username;
        this.email = email;

    }
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
