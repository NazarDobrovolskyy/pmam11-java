// DbFacadeTest.java
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import db.DbFacade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DbFacadeTest {
    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @InjectMocks
    private DbFacade dbFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDatabase() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        dbFacade.createDatabase("test_db");
        verify(mockStatement).executeUpdate("CREATE DATABASE test_db");
    }

    @Test
    public void testCreateTable() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        dbFacade.createTable("test_db", "test_table");
        verify(mockStatement).executeUpdate("CREATE TABLE test_table (id INT PRIMARY KEY, name VARCHAR(255))");
    }

    @Test
    public void testInsertData() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        dbFacade.insertData("test_db", "test_table", 1, "John");
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setString(2, "John");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testUpdateData() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        dbFacade.updateData("test_db", "test_table", 1, "Jane");
        verify(mockPreparedStatement).setString(1, "Jane");
        verify(mockPreparedStatement).setInt(2, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteData() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        dbFacade.deleteData("test_db", "test_table", 1);
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testIntegration() throws SQLException {
        String dbUser = "ADD_YOUR_DB_USER";
        String dbPassword = "ADD_YOUR_DB_PASSWORD";

        DbFacade realDbFacade = new DbFacade(dbUser, dbPassword);

        // Create a test database
        realDbFacade.createDatabase("test_db_integration");

        // Create a test table
        realDbFacade.createTable("test_db_integration", "test_table_integration");

        // Insert data
        realDbFacade.insertData("test_db_integration", "test_table_integration", 1, "Alice");

        // Verify data is inserted
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db_integration", "your_username", "your_password");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM test_table_integration")) {

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                assertEquals(1, id);
                assertEquals("Alice", name);
            } else {
                throw new AssertionError("No data found");
            }
        }
    }
}
