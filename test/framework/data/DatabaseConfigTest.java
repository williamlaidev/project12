package framework.data;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabaseConfigTest {

    private DatabaseConfig databaseConfig = new DatabaseConfig();

    @Test
    public void testConnect() {
        Connection connection = databaseConfig.connect();
        assertNotNull(connection, "Connection should not be null");
        try {
            connection.close();
        } catch (SQLException e) {
            fail("Failed to close connection");
        }
    }

    @Test
    public void testCreateNewDatabase() {
        databaseConfig.createNewDatabase();
    }
}
