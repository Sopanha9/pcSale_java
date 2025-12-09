package com.pcsale.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Expense Model Class
 * Represents business expenses
 */
public class Expense {
    private int id;
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private Integer userId;
    private String userName;
    private LocalDateTime createdAt;
    
    // Constructors
    public Expense() {
        this.expenseDate = LocalDate.now();
    }
    
    public Expense(String description, BigDecimal amount, LocalDate expenseDate) {
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public LocalDate getExpenseDate() {
        return expenseDate;
    }
    
    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return description + " - $" + amount;
    }
}
