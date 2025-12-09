package com.pcsale.model;

import java.time.LocalDateTime;

/**
 * User Model Class
 * Represents a system user (admin, cashier, manager)
 */
public class User {
    private int id;
    private String username;
    private String fullName;
    private String password;
    private String role; // admin, cashier, manager
    private String status; // active, inactive
    private LocalDateTime createdAt;
    
    // Constructors
    public User() {
        this.role = "cashier";
        this.status = "active";
    }
    
    public User(String username, String fullName, String password, String role) {
        this();
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // Utility methods
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(role);
    }
    
    public boolean isManager() {
        return "manager".equalsIgnoreCase(role);
    }
    
    public boolean isCashier() {
        return "cashier".equalsIgnoreCase(role);
    }
    
    public boolean isActive() {
        return "active".equalsIgnoreCase(status);
    }
    
    @Override
    public String toString() {
        return fullName + " (" + username + ")";
    }
}
