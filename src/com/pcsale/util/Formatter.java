package com.pcsale.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Formatter Utility - Formats numbers, currency, dates
 */
public class Formatter {
    
    private static final DecimalFormat currencyFormat = new DecimalFormat("#,##0.00");
    private static final DecimalFormat numberFormat = new DecimalFormat("#,##0");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat displayDateFormat = new SimpleDateFormat("MMM dd, yyyy");
    private static final SimpleDateFormat displayDateTimeFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
    
    /**
     * Format number as currency with $ symbol
     */
    public static String formatCurrency(double amount) {
        return "$" + currencyFormat.format(amount);
    }
    
    /**
     * Format number with comma separators
     */
    public static String formatNumber(double number) {
        return numberFormat.format(number);
    }
    
    /**
     * Format date as yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        if (date == null) return "";
        return dateFormat.format(date);
    }
    
    /**
     * Format datetime as yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        return dateTimeFormat.format(date);
    }
    
    /**
     * Format date for display (MMM dd, yyyy)
     */
    public static String formatDisplayDate(Date date) {
        if (date == null) return "";
        return displayDateFormat.format(date);
    }
    
    /**
     * Format datetime for display (MMM dd, yyyy hh:mm a)
     */
    public static String formatDisplayDateTime(Date date) {
        if (date == null) return "";
        return displayDateTimeFormat.format(date);
    }
}
