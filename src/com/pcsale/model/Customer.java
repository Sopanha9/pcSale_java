package com.pcsale.model;

import java.time.LocalDateTime;

/**
 * Customer Model Class
 * Represents a customer in the system
 */
public class Customer {
    private int id;
    private String customerCode;
    private String name;
    private String phone;
    private String email;
    private String address;
    private int points;
    private LocalDateTime createdAt;
    
    // Constructors
    public Customer() {
        this.points = 0;
    }
    
    public Customer(String customerCode, String name, String phone) {
        this();
        this.customerCode = customerCode;
        this.name = name;
        this.phone = phone;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCustomerCode() {
        return customerCode;
    }
    
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return name + " (" + customerCode + ")";
    }
}
