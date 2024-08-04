//package interface_adapter.data;
//
//import entity.*;
//import org.junit.jupiter.api.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SQLiteRestaurantRepositoryTest {
//    private static final String DB_URL = "jdbc:sqlite::memory:";
//    private SQLiteRestaurantRepository repository;
//
//    @BeforeEach
//    void setUp() {
//        repository = new SQLiteRestaurantRepository();
//        try (Connection conn = DriverManager.getConnection(DB_URL)) {
//            String sql = "CREATE TABLE IF NOT EXISTS restaurants ("
//                    + "restaurantId TEXT PRIMARY KEY,"
//                    + "name TEXT NOT NULL,"
//                    + "latitude REAL NOT NULL,"
//                    + "longitude REAL NOT NULL,"
//                    + "address TEXT NOT NULL,"
//                    + "dishType TEXT,"
//                    + "averageRating REAL NOT NULL,"
//                    + "photoUrl TEXT"
//                    + ");";
//            Statement stmt = conn.createStatement();
//            stmt.execute(sql);
//        } catch (SQLException e) {
//            Logger logger = LoggerFactory.getLogger(SQLiteRestaurantRepositoryTest.class);
//            logger.error("Error creating restaurants table", e);
//        }
//    }
//
//    @Test
//    void testAddRestaurantWithDishType() {
//        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(40.7128, -74.0060), "Test Address", DishType.AMERICAN, 4.5, null, null, null);
//        boolean result = repository.add(restaurant);
//        assertTrue(result);
//
//        Optional<Restaurant> foundRestaurant = repository.findById("1");
//        assertTrue(foundRestaurant.isPresent());
//        assertEquals("Test Restaurant", foundRestaurant.get().getName());
//        assertEquals(DishType.AMERICAN, foundRestaurant.get().getDishType());
//    }
//
//    @Test
//    void testAddRestaurantWithNullDishType() {
//        Restaurant restaurant = new Restaurant("2", "Null DishType Restaurant", new Location(34.0522, -118.2437), "Null Address", null, 4.0, null, null, null);
//        boolean result = repository.add(restaurant);
//        assertTrue(result);
//
//        Optional<Restaurant> foundRestaurant = repository.findById("2");
//        assertTrue(foundRestaurant.isPresent());
//        assertEquals("Null DishType Restaurant", foundRestaurant.get().getName());
//        assertNull(foundRestaurant.get().getDishType());
//    }
//
//    @Test
//    void testFindByDishType() {
//        Restaurant restaurant1 = new Restaurant("3", "Italian Restaurant", new Location(37.7749, -122.4194), "Italian Address", DishType.ITALIAN, 4.7, null, null, null);
//        Restaurant restaurant2 = new Restaurant("4", "Mexican Restaurant", new Location(51.5074, -0.1278), "Mexican Address", DishType.MEXICAN, 4.2, null, null, null);
//        repository.add(restaurant1);
//        repository.add(restaurant2);
//
//        Optional<Restaurant> foundRestaurant = repository.findByName("Italian Restaurant");
//        assertTrue(foundRestaurant.isPresent());
//        assertEquals(DishType.ITALIAN, foundRestaurant.get().getDishType());
//    }
//
//    @Test
//    void testUpdateRestaurantDishType() {
//        Restaurant restaurant = new Restaurant("5", "Update DishType Restaurant", new Location(40.730610, -73.935242), "Update Address", DishType.CHINESE, 4.6, null, null, null);
//        repository.add(restaurant);
//
//        restaurant = new Restaurant("5", "Updated DishType Restaurant", new Location(40.730610, -73.935242), "Updated Address", DishType.JAPANESE, 4.8, null, null, null);
//        boolean result = repository.update(restaurant);
//        assertTrue(result);
//
//        Optional<Restaurant> updatedRestaurant = repository.findById("5");
//        assertTrue(updatedRestaurant.isPresent());
//        assertEquals(DishType.JAPANESE, updatedRestaurant.get().getDishType());
//    }
//
//    @Test
//    void testDeleteRestaurantWithDishType() {
//        Restaurant restaurant = new Restaurant("6", "Delete DishType Restaurant", new Location(34.0522, -118.2437), "Delete Address", DishType.MEDITERRANEAN, 4.4, null, null, null);
//        repository.add(restaurant);
//
//        boolean result = repository.delete("6");
//        assertTrue(result);
//
//        Optional<Restaurant> deletedRestaurant = repository.findById("6");
//        assertFalse(deletedRestaurant.isPresent());
//    }
//}
