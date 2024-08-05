package framework.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides utility methods for managing SQLite database connections and configurations.
 * Includes methods to establish a connection to the database and to create a new database.
 */
public class DatabaseConfig {
    private static final String URL = "jdbc:sqlite:app_database.sqlite";

    /**
     * Establishes a connection to the SQLite database specified by the {@code URL}.
     * Prints a message indicating the success or failure of the connection attempt.
     *
     * @return a {@link Connection} object to the SQLite database, or {@code null} if the connection could not be established
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Creates a new SQLite database by establishing a connection and printing database metadata.
     * Prints the driver name and a message indicating that a new database has been created.
     */
    public static void createNewDatabase() {
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
