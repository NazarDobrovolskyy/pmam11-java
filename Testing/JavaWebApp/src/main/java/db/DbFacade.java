package db;

// DbFacade.java
import java.sql.*;

public class DbFacade {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private String dbUserName;
    private String dbPassword;

    public DbFacade(String dbName, String dbPassword) {
        this.dbUserName = dbName;
        this.dbPassword = dbPassword;
    }

    public void createDatabase(String dbName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, dbUserName, dbPassword);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE DATABASE " + dbName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String dbName, String tableName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + dbName, dbUserName, dbPassword);
             Statement statement = connection.createStatement()) {

            String createTableQuery = "CREATE TABLE " + tableName + " (id INT PRIMARY KEY, name VARCHAR(255))";
            statement.executeUpdate(createTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String dbName, String tableName, int id, String name) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + dbName, dbUserName, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + " (id, name) VALUES (?, ?)")) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(String dbName, String tableName, int id, String newName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + dbName, dbUserName, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + tableName + " SET name = ? WHERE id = ?")) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(String dbName, String tableName, int id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + dbName, dbUserName, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String dbName, String tableName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + dbName, dbUserName, dbPassword);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbName) {
        this.dbUserName = dbName;
    }
}
