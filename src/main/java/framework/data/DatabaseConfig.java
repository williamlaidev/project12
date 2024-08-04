package framework.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides utility methods for managing SQLite database connections and configurations.
 */
public class DatabaseConfig {
    private static final String URL = "jdbc:sqlite:app_database.sqlite";

    /**
     * Establishes a connection to the SQLite database specified by the {@code URL}.
     *
     * @return a {@link Connection} object to the SQLite database, or {@code null} if the connection could not be established
     */
    public Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Creates a new SQLite database by establishing a connection and printing database metadata.
     */
    public void createNewDatabase() {
        try (Connection conn = connect()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
