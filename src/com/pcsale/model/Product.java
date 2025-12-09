package com.pcsale.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Product Model Class
 * Represents a product in the inventory
 */
public class Product {
    private int id;
    private String barcode;
    private String name;
    private Integer categoryId;
    private String categoryName;
    private Integer supplierId;
    private String supplierName;
    private BigDecimal costPrice;
    private BigDecimal sellingPrice;
    private int stockQuantity;
    private int reorderLevel;
    private String unit;
    private String image;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Product() {
        this.status = "active";
        this.unit = "pcs";
        this.reorderLevel = 10;
        this.stockQuantity = 0;
    }
    
    public Product(String barcode, String name, BigDecimal costPrice, BigDecimal sellingPrice) {
        this();
        this.barcode = barcode;
        this.name = name;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public Integer getSupplierId() {
        return supplierId;
    }
    
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    
    public String getSupplierName() {
        return supplierName;
    }
    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public BigDecimal getCostPrice() {
        return costPrice;
    }
    
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
    
    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }
    
    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public int getReorderLevel() {
        return reorderLevel;
    }
    
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
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
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Utility methods
    public boolean isLowStock() {
        return stockQuantity <= reorderLevel;
    }
    
    public BigDecimal getProfit() {
        return sellingPrice.subtract(costPrice);
    }
    
    public double getProfitMargin() {
        if (sellingPrice.compareTo(BigDecimal.ZERO) == 0) return 0;
        return getProfit().divide(sellingPrice, 4, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).doubleValue();
    }
    
    @Override
    public String toString() {
        return name + " (" + barcode + ")";
    }
}
