package interface_adapter;

import entity.DishType;
import entity.Location;
import entity.Restaurant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantJsonRepositoryTest {
    private static final String TEST_FILE_PATH = "test_restaurant.json";
    private RestaurantJsonRepository repository;

    @BeforeEach
    void setUp() {
        // Set up a new repository before each test
        repository = new RestaurantJsonRepository(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test file after each test
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    @Test
    void getRestaurantById() {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(40.7128, -74.0060),
                "123 Test St", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", "Great place!");
        repository.saveRestaurant(restaurant);

        Restaurant found = repository.getRestaurantById("1");
        assertNotNull(found);
        assertEquals("Test Restaurant", found.getName());
    }

    @Test
    void getRestaurantByName() {
        Restaurant restaurant = new Restaurant("2", "Another Restaurant", new Location(34.0522, -118.2437),
                "456 Another St", DishType.CHINESE, 3.8, "http://example.com/photo.jpg", "Good food!");
        repository.saveRestaurant(restaurant);

        Restaurant found = repository.getRestaurantByName("Another Restaurant");
        assertNotNull(found);
        assertEquals("2", found.getRestaurantId());
    }

    @Test
    void getAllRestaurant() {
        Restaurant restaurant1 = new Restaurant("3", "Restaurant One", new Location(51.5074, -0.1278),
                "789 Restaurant St", DishType.JAPANESE, 4.2, "http://example.com/photo.jpg", "Very nice.");
        Restaurant restaurant2 = new Restaurant("4", "Restaurant Two", new Location(48.8566, 2.3522),
                "101 Restaurant Ave", DishType.MEXICAN, 4.0, "http://example.com/photo.jpg", "Excellent!");
        repository.saveRestaurant(restaurant1);
        repository.saveRestaurant(restaurant2);

        List<Restaurant> restaurants = repository.getAllRestaurant();
        assertEquals(2, restaurants.size());
    }

    @Test
    void saveRestaurant() {
        Restaurant restaurant = new Restaurant("5", "Save Test Restaurant", new Location(40.730610, -73.935242),
                "102 Save St", DishType.INDIAN, 4.7, "http://example.com/photo.jpg", "Delicious!");

        boolean result = repository.saveRestaurant(restaurant);
        assertTrue(result);

        Restaurant found = repository.getRestaurantById("5");
        assertNotNull(found);
        assertEquals("Save Test Restaurant", found.getName());
    }

    @Test
    void updateRestaurant() {
        Restaurant restaurant = new Restaurant("6", "Update Test Restaurant", new Location(37.7749, -122.4194),
                "103 Update St", DishType.CARIBBEAN, 4.3, "http://example.com/photo.jpg", "Nice place!");
        repository.saveRestaurant(restaurant);

        Restaurant updatedRestaurant = new Restaurant("6", "Updated Restaurant", new Location(37.7749, -122.4194),
                "103 Update St", DishType.CARIBBEAN, 4.8, "http://example.com/photo.jpg", "Even better!");

        boolean result = repository.updateRestaurant(updatedRestaurant);
        assertTrue(result);

        Restaurant found = repository.getRestaurantById("6");
        assertNotNull(found);
        assertEquals("Updated Restaurant", found.getName());
        assertEquals(4.8, found.getAverageRating());
    }

    @Test
    void deleteRestaurantById() {
        Restaurant restaurant = new Restaurant("7", "Delete Test Restaurant", new Location(34.0522, -118.2437),
                "104 Delete St", DishType.VIETNAMESE, 3.9, "http://example.com/photo.jpg", "Good spot!");
        repository.saveRestaurant(restaurant);

        boolean result = repository.deleteRestaurantById("7");
        assertTrue(result);

        Restaurant found = repository.getRestaurantById("7");
        assertNull(found);
    }

    @Test
    void deleteRestaurantByName() {
        Restaurant restaurant = new Restaurant("8", "Delete By Name Restaurant", new Location(34.0522, -118.2437),
                "105 Delete Ave", DishType.HALAL, 3.9, "http://example.com/photo.jpg", "Awesome!");
        repository.saveRestaurant(restaurant);

        boolean result = repository.deleteRestaurantByName("Delete By Name Restaurant");
        assertTrue(result);

        Restaurant found = repository.getRestaurantByName("Delete By Name Restaurant");
        assertNull(found);
    }
}
