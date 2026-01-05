# Database Migration Instructions

Since we have added new fields specific to PC Sales (Specifications and Warranty Period), you need to update your local database schema.

## Steps:

1. **Make sure your WAMP/MySQL server is running.**
2. Open a terminal in the project root (`c:\pcSale_java`).
3. Compile the migration tool:
   ```bash
   mkdir bin
   javac -cp "lib/mysql-connector-j-8.2.0.jar" -sourcepath src -d bin src/com/pcsale/util/DatabaseMigration.java
   ```
4. Run the migration tool:
   ```bash
   java -cp "bin;lib/mysql-connector-j-8.2.0.jar" com.pcsale.util.DatabaseMigration
   ```

You should see "Migration completed successfully."

After this, you can run the application normally.
