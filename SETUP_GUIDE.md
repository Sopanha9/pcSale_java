# Quick Setup Guide - PC Sale POS System

## Step-by-Step Installation

### 1. Prerequisites Check ✓

Before starting, ensure you have:

- [x] WAMP Server installed and running
- [x] JDK 8 or higher installed
- [x] MySQL running on port 3306

**Check Java Installation:**

```powershell
java -version
javac -version
```

### 2. Database Setup (5 minutes)

#### A. Start WAMP Server

1. Run WAMP Server
2. Wait for the icon to turn green
3. Click WAMP icon > MySQL > MySQL Console
4. Press Enter (default password is empty)

#### B. Create Database

1. Open browser: http://localhost/phpmyadmin
2. Click "New" to create database
3. Name it: `pc_sale_db`
4. Click "Create"

#### C. Import Database Structure

1. Select `pc_sale_db` database
2. Click "Import" tab
3. Choose file: `pc_sale.sql` (from Downloads)
4. Click "Go"

#### D. Import Sample Data (Optional)

1. Click "Import" tab again
2. Choose file: `sample_data.sql`
3. Click "Go"

### 3. Download MySQL JDBC Driver (2 minutes)

#### Option A: Direct Download

1. Visit: https://dev.mysql.com/downloads/connector/j/
2. Select "Platform Independent"
3. Download the ZIP file
4. Extract the ZIP
5. Find file: `mysql-connector-j-8.x.x.jar`
6. Copy to: `C:\Users\Johnn\Documents\trackRecord\lib\`

#### Option B: Quick Download (Recommended)

```powershell
# Run this in PowerShell to download automatically
cd C:\Users\Johnn\Documents\trackRecord\lib
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.2.0/mysql-connector-j-8.2.0.jar" -OutFile "mysql-connector-j-8.2.0.jar"
```

### 4. Verify File Structure

Your project should look like this:

```
trackRecord/
├── lib/
│   └── mysql-connector-j-8.2.0.jar  ← Must be here!
├── src/
│   └── com/pcsale/...
├── build.ps1
├── build.bat
└── README.md
```

### 5. Build and Run (1 minute)

#### Option A: Using PowerShell Script (Recommended)

```powershell
cd C:\Users\Johnn\Documents\trackRecord
.\build.ps1
```

#### Option B: Using Batch File

```cmd
cd C:\Users\Johnn\Documents\trackRecord
build.bat
```

#### Option C: Manual Commands

```powershell
# Create bin directory
New-Item -ItemType Directory -Path bin -Force

# Compile
javac -d bin -cp "lib\*" src\com\pcsale\model\*.java src\com\pcsale\dao\*.java src\com\pcsale\util\*.java src\com\pcsale\gui\*.java

# Run
java -cp "bin;lib\*" com.pcsale.gui.LoginFrame
```

### 6. First Login

**Default Credentials:**

- Username: `admin`
- Password: `admin123`

**If login fails:**

1. Check WAMP is running (green icon)
2. Verify database exists: http://localhost/phpmyadmin
3. Check MySQL service is running
4. Review console for error messages

### 7. Initial Configuration

After first login:

1. **Change Admin Password**

   - Navigate to Users (Admin only)
   - Edit admin user
   - Change password

2. **Add Categories**

   - If you didn't import sample data
   - Navigate to Categories
   - Add your product categories

3. **Add Products**

   - Navigate to Products
   - Click "Add Product"
   - Fill in product details

4. **Test POS System**
   - Navigate to POS / Sales
   - Search for a product
   - Add to cart
   - Complete a test sale

## Troubleshooting

### "Database connection failed"

**Solution:**

- Ensure WAMP is running (green icon)
- Check MySQL service: WAMP > MySQL > Service > Start/Resume
- Verify database name: `pc_sale_db`
- Check port 3306 is not blocked

### "JDBC Driver not found"

**Solution:**

- Download MySQL Connector/J
- Place `.jar` file in `lib/` folder
- Ensure filename matches in classpath

### "javac is not recognized"

**Solution:**

- Install JDK (not just JRE)
- Add Java to PATH:
  - System Properties > Environment Variables
  - Add: `C:\Program Files\Java\jdk-XX\bin`

### Compilation Errors

**Solution:**

- Ensure all `.java` files are in `src/com/pcsale/`
- Check Java version: `javac -version`
- Try cleaning: Delete `bin/` folder and rebuild

### "Port 3306 already in use"

**Solution:**

- Check for other MySQL instances
- Stop conflicting services
- Or change MySQL port in WAMP config and `DatabaseConfig.java`

## Quick Commands Reference

### Compile Only

```powershell
javac -d bin -cp "lib\*" src\com\pcsale\**\*.java
```

### Run Only (after compile)

```powershell
java -cp "bin;lib\*" com.pcsale.gui.LoginFrame
```

### Clean Build

```powershell
Remove-Item -Recurse -Force bin
New-Item -ItemType Directory -Path bin
javac -d bin -cp "lib\*" src\com\pcsale\**\*.java
```

### Check Database Connection

```powershell
# In MySQL Console
mysql -u root
SHOW DATABASES;
USE pc_sale_db;
SHOW TABLES;
SELECT * FROM users;
```

## Next Steps

1. ✓ Login to the system
2. ✓ Explore the dashboard
3. ✓ Add some products
4. ✓ Create test customers
5. ✓ Make a test sale
6. ✓ Check sales history
7. ✓ Review reports

## Additional Resources

- **MySQL Documentation**: https://dev.mysql.com/doc/
- **Java Swing Tutorial**: https://docs.oracle.com/javase/tutorial/uiswing/
- **WAMP Server**: https://www.wampserver.com/

## Support

If you encounter issues:

1. Check the console output for error messages
2. Review the troubleshooting section above
3. Verify all prerequisites are installed
4. Check database connection in phpMyAdmin

## Tips for Better Performance

1. **Regular Backups**

   - Export database regularly from phpMyAdmin
   - Keep backup of products and sales data

2. **User Management**

   - Create separate users for cashiers
   - Use manager accounts for inventory management
   - Keep admin password secure

3. **Stock Management**

   - Set appropriate reorder levels
   - Monitor low stock alerts
   - Update stock regularly

4. **Customer Data**
   - Keep customer information updated
   - Track loyalty points
   - Use customer data for marketing

Enjoy using your PC Sale POS System! 🚀
