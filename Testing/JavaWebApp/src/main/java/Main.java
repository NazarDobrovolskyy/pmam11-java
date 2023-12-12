import db.DbFacade;

public class Main {
    public static void main(String[] args) {

        String dbUser = "ADD_YOUR_DB_USER";
        String dbPassword = "ADD_YOUR_DB_PASSWORD";

        // Instantiate the DbFacade
        DbFacade dbFacade = new DbFacade(dbUser, dbPassword);

        // Create a new database
        String newDbName = "new_database";
        dbFacade.createDatabase(newDbName);

        // Create a new table in the database
        String newTableName = "new_table";
        dbFacade.createTable(newDbName, newTableName);

        // Insert data into the table
        dbFacade.insertData(newDbName, newTableName, 1, "John Doe");

        // Update data in the table
        dbFacade.updateData(newDbName, newTableName, 1, "John Doe Updated");

        // Delete data from the table
        dbFacade.deleteData(newDbName, newTableName, 1);

        // Delete a table from the database
        dbFacade.deleteTable(newDbName, newTableName);
    }
}
