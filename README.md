# PC Sale POS System

A complete Point of Sale (POS) system for PC sales built with Java Swing and MySQL.

## Features

- **User Authentication** - Secure login system with role-based access (Admin, Manager, Cashier)
- **Point of Sale** - Fast and intuitive sales interface with barcode scanning
- **Product Management** - Complete CRUD operations for products with category support
- **Customer Management** - Customer database with loyalty points tracking
- **Inventory Management** - Stock tracking with low stock alerts
- **Sales History** - Complete transaction records and reporting
- **Dashboard** - Real-time sales and inventory overview

## Technology Stack

- **Language**: Java 8+
- **GUI Framework**: Java Swing
- **Database**: MySQL 8.0+
- **JDBC Driver**: MySQL Connector/J
- **Server**: WAMP Server (for development)

## Project Structure

```
trackRecord/
├── src/
│   └── com/
│       └── pcsale/
│           ├── dao/              # Data Access Objects
│           │   ├── CategoryDAO.java
│           │   ├── CustomerDAO.java
│           │   ├── ProductDAO.java
│           │   ├── SaleDAO.java
│           │   └── UserDAO.java
│           ├── gui/              # GUI Components
│           │   ├── CustomerPanel.java
│           │   ├── LoginFrame.java
│           │   ├── MainDashboard.java
│           │   ├── POSPanel.java
│           │   └── ProductPanel.java
│           ├── model/            # Entity Classes
│           │   ├── Category.java
│           │   ├── Customer.java
│           │   ├── Expense.java
│           │   ├── Product.java
│           │   ├── Sale.java
│           │   ├── SaleItem.java
│           │   ├── Supplier.java
│           │   └── User.java
│           └── util/             # Utility Classes
│               ├── DatabaseConfig.java
│               ├── Formatter.java
│               └── SessionManager.java
├── lib/                          # External libraries
│   └── mysql-connector-j-8.x.x.jar
└── resources/                    # Resources (images, etc.)
```

## Database Setup

### 1. Start WAMP Server

- Make sure WAMP server is installed and running
- Default configuration uses:
  - Host: `localhost:3306`
  - Database: `pc_sale_db`
  - Username: `root`
  - Password: (empty)

### 2. Import Database

1. Open phpMyAdmin (http://localhost/phpmyadmin)
2. Create a new database named `pc_sale_db`
3. Import the provided SQL file: `pc_sale.sql`

### 3. Create Default Admin User

Run this SQL query to create a default admin user:

```sql
INSERT INTO users (username, full_name, password, role, status)
VALUES ('admin', 'Administrator', 'admin123', 'admin', 'active');
```

**Default Login Credentials:**

- Username: `admin`
- Password: `admin123`

## Installation & Setup

### Prerequisites

- JDK 8 or higher
- WAMP Server (or any MySQL server)
- MySQL Connector/J JDBC Driver

### Steps

1. **Download MySQL Connector**

   - Download from: https://dev.mysql.com/downloads/connector/j/
   - Extract `mysql-connector-j-8.x.x.jar`
   - Place it in the `lib/` folder

2. **Configure Database**

   - Edit `src/com/pcsale/util/DatabaseConfig.java` if your database settings differ
   - Default settings work with standard WAMP installation

3. **Compile the Project**

   ```powershell
   # Navigate to project directory
   cd C:\Users\Johnn\Documents\trackRecord

   # Compile all Java files
   javac -d bin -cp "lib\*" src\com\pcsale\model\*.java src\com\pcsale\dao\*.java src\com\pcsale\util\*.java src\com\pcsale\gui\*.java
   ```

4. **Run the Application**
   ```powershell
   # Run from bin directory
   java -cp "bin;lib\*" com.pcsale.gui.LoginFrame
   ```

## Quick Start Guide

### First Time Setup

1. Start WAMP server
2. Import the database
3. Create admin user
4. Run the application
5. Login with admin credentials

### Adding Products

1. Navigate to **Products** menu
2. Click **Add Product**
3. Fill in product details (barcode, name, price, stock)
4. Save

### Making a Sale

1. Navigate to **POS / Sales**
2. Search for products by name or barcode
3. Add products to cart
4. Enter quantity
5. Enter amount paid
6. Click **Complete Sale**

### Managing Customers

1. Navigate to **Customers** menu
2. Add new customers with contact details
3. Track customer purchases and loyalty points

## User Roles & Permissions

- **Admin**: Full access to all features including user management
- **Manager**: Access to products, customers, sales, and reports
- **Cashier**: Access to POS and basic customer features

## Troubleshooting

### Database Connection Error

- Ensure WAMP server is running
- Check that MySQL service is active
- Verify database name is `pc_sale_db`
- Check credentials in `DatabaseConfig.java`

### JDBC Driver Not Found

- Ensure `mysql-connector-j-8.x.x.jar` is in the `lib/` folder
- Include it in classpath when compiling and running

### Port Already in Use

- Change MySQL port in WAMP configuration
- Update port in `DatabaseConfig.java`

## Sample Data

To populate the database with sample data for testing:

```sql
-- Add categories
INSERT INTO categories (name, description) VALUES
('Processors', 'CPU and processors'),
('Graphics Cards', 'GPU and video cards'),
('Memory', 'RAM modules'),
('Storage', 'HDD and SSD drives');

-- Add sample products
INSERT INTO products (barcode, name, category_id, cost_price, selling_price, stock_quantity) VALUES
('P001', 'Intel Core i7-12700K', 1, 350.00, 450.00, 15),
('P002', 'AMD Ryzen 7 5800X', 1, 300.00, 400.00, 20),
('G001', 'NVIDIA RTX 3060 Ti', 2, 450.00, 550.00, 10),
('M001', 'Corsair Vengeance 16GB DDR4', 3, 60.00, 80.00, 30),
('S001', 'Samsung 970 EVO 1TB NVMe', 4, 120.00, 150.00, 25);
```

## Future Enhancements

- Receipt printing functionality
- Sales reports and analytics
- Barcode scanner integration
- Employee attendance tracking
- Email notifications for low stock
- Export reports to PDF/Excel

## License

This project is created for educational purposes.

## Contact

For questions or support, please contact the development team.
