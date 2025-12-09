package com.pcsale.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Configuration and Connection Manager
 * Manages MySQL database connections for WAMP server
 */
public class DatabaseConfig {
    
    // Database credentials for WAMP server
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pc_sale_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Default WAMP password is empty
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static Connection connection = null;
    
    /**
     * Get database connection
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load MySQL JDBC Driver
                Class.forName(DB_DRIVER);
                
                // Create connection
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * Close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test database connection
     * @return true if connection is successful
     */
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
