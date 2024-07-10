package main.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteDemo {

    // SQLite database URL
    private static final String URL = "jdbc:sqlite:/home/user/java101/project12/src/main/resources/mydatabase.sqlite";

    public static void main(String[] args) {
        try {
            // Register SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Open a connection
            Connection conn = DriverManager.getConnection(URL);

            // Perform operations
            createTable(conn);
            insertData(conn);
            queryData(conn);

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        // SQL statement to create a table
        String sql = "CREATE TABLE IF NOT EXISTS restaurants (" +
                "restaurantId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "latitude REAL," +
                "longitude REAL," +
                "address TEXT," +
                "dishType TEXT," +
                "averageRating REAL," +
                "photoUrl TEXT," +
                "summarizedReview TEXT" +
                ")";

        // Execute the SQL statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Table 'restaurants' created successfully.");
        }
    }

    private static void insertData(Connection conn) throws SQLException {
        // SQL statement to insert data
        String sql = "INSERT INTO restaurants (name, latitude, longitude, address, dishType, averageRating, photoUrl, summarizedReview) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "Restaurant C");
            stmt.setDouble(2, 37.7749); // Example latitude
            stmt.setDouble(3, -122.4194); // Example longitude
            stmt.setString(4, "789 Elm St, San Francisco, CA");
            stmt.setString(5, "American");
            stmt.setDouble(6, 4.0);
            stmt.setString(7, "http://example.com/photo3.jpg");
            stmt.setString(8, "Classic American dishes.");

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new restaurant has been inserted.");
            }
        }
    }

    private static void queryData(Connection conn) throws SQLException {
        // SQL query to retrieve data
        String sql = "SELECT * FROM restaurants";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Process the result set
            while (rs.next()) {
                int id = rs.getInt("restaurantId");
                String name = rs.getString("name");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String address = rs.getString("address");
                String dishType = rs.getString("dishType");
                double averageRating = rs.getDouble("averageRating");
                String photoUrl = rs.getString("photoUrl");
                String summarizedReview = rs.getString("summarizedReview");

                System.out.println("Restaurant ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Location: " + latitude + ", " + longitude);
                System.out.println("Address: " + address);
                System.out.println("Dish Type: " + dishType);
                System.out.println("Average Rating: " + averageRating);
                System.out.println("Photo URL: " + photoUrl);
                System.out.println("Summarized Review: " + summarizedReview);
                System.out.println();
            }
        }
    }
}