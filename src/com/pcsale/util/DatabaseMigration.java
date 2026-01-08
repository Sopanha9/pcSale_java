package com.pcsale.util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseMigration {
    
    public static void main(String[] args) {
        migrate();
    }
    
    public static void migrate() {
        System.out.println("Starting database migration...");
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
             
            // Add specifications column
            try {
                stmt.execute("ALTER TABLE products ADD COLUMN specifications TEXT");
                System.out.println("Added 'specifications' column.");
            } catch (SQLException e) {
                if (e.getMessage().contains("Duplicate column")) {
                     System.out.println("'specifications' column already exists.");
                } else {
                    // Ignore error if column exists (MySQL doesn't support IF NOT EXISTS for columns in all versions easily)
                    // or check specific error code
                    System.out.println("Message: " + e.getMessage());
                }
            }

            // Add warranty_period column
            try {
                stmt.execute("ALTER TABLE products ADD COLUMN warranty_period INT DEFAULT 0");
                System.out.println("Added 'warranty_period' column.");
            } catch (SQLException e) {
                 if (e.getMessage().contains("Duplicate column")) {
                     System.out.println("'warranty_period' column already exists.");
                } else {
                    System.out.println("Message: " + e.getMessage());
                }
            }

            // Seed Categories
            String[] categories = {
                "Laptops", "Desktops", "CPUs", "GPUs", "Motherboards", 
                "RAM", "Storage (SSD/HDD)", "Power Supplies", "Cases", "Monitors", "Peripherals"
            };

            for (String cat : categories) {
                // Check if exists
                String checkSql = "SELECT id FROM categories WHERE name = '" + cat + "'";
                if (!stmt.executeQuery(checkSql).next()) {
                    stmt.execute("INSERT INTO categories (name, description) VALUES ('" + cat + "', 'PC Component')");
                     System.out.println("Added category: " + cat);
                }
            }
            
            System.out.println("Migration completed successfully.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
