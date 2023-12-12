# Database Decade Code

The Database Decade Code is a Java application that demonstrates basic database operations using JDBC. It includes a `DbFacade` class encapsulating CRUD operations on an existing table, the creation of a new table, and the creation of a new database.


## Configure MySQL Database

1. Create a MySQL database.
2. Update the database connection details in the DbFacade class

## Usage

The DbFacade class provides methods for database operations:

* ```createDatabase(String dbName)```: Creates a new database.
* ```createTable(String dbName, String tableName)```: Creates a new table in a specified database.
* ```insertData(String dbName, String tableName, int id, String name)```: Inserts data into an existing table.
* ```updateData(String dbName, String tableName, int id, String newName)```: Updates data in an existing table.
* ```deleteData(String dbName, String tableName, int id)```: Deletes data from an existing table.
* ```deleteTable(String dbName, String tableName)```: Deletes existing table.

## Features

* **Database Creation**: Easily create a new database using ```createDatabase``` method.
* **Table Creation**: Create tables with custom schema using ```createTable``` method.
* **CRUD Operations**: Perform CRUD operations (Create, Read, Update, Delete) on an existing table.