package com.pcsale.util;

import com.pcsale.model.User;

/**
 * Session Manager - Manages current user session
 */
public class SessionManager {
    private static User currentUser;
    
    /**
     * Set the current logged-in user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    /**
     * Get the current logged-in user
     */
    public static User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if a user is logged in
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Logout current user
     */
    public static void logout() {
        currentUser = null;
    }
    
    /**
     * Check if current user is admin
     */
    public static boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }
    
    /**
     * Check if current user is manager
     */
    public static boolean isManager() {
        return currentUser != null && currentUser.isManager();
    }
    
    /**
     * Check if current user has admin or manager role
     */
    public static boolean hasManagementAccess() {
        return isAdmin() || isManager();
    }
}
