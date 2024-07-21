package interface_adapter;

import data_access.RestaurantDataAccess;
import entity.Location;
import entity.Restaurant;
import entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryRestaurantRepositoryTest {
    private InMemoryRestaurantRepository repository;
    private RestaurantDataAccess mockDataAccess;

    @BeforeEach
    void setUp() {
        // Mock RestaurantDataAccess to avoid actual file operations
        mockDataAccess = new RestaurantDataAccess() {
            private final List<Restaurant> restaurants = new ArrayList<>();

            @Override
            public List<Restaurant> loadRestaurants() {
                return new ArrayList<>(restaurants);
            }

            @Override
            public void saveRestaurants(List<Restaurant> restaurants) {
                this.restaurants.clear();
                this.restaurants.addAll(restaurants);
            }
        };
        repository = new InMemoryRestaurantRepository(mockDataAccess);
    }

    @AfterEach
    void tearDown() {
        // Clean up resources if needed
    }

    @Test
    void testAddRestaurant() {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(0, 0), "Address", null, 0, null, Arrays.asList(new Review("1", "Author1", "Great food!", false)),null);
        boolean added = repository.add(restaurant);
        assertTrue(added, "Restaurant should be added successfully");
        assertEquals(1, repository.getAll().size(), "There should be 1 restaurant in the repository");
        assertEquals(restaurant, repository.getById("1").orElse(null), "The added restaurant should be retrievable by ID");
    }

    @Test
    void testAddDuplicateRestaurant() {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(0, 0), "Address", null, 0, null, Arrays.asList(new Review("1", "Author1", "Great food!", false)),null);
        repository.add(restaurant);
        boolean added = repository.add(restaurant);
        assertFalse(added, "Adding a restaurant with an existing ID should fail");
    }

    @Test
    void testUpdateRestaurant() {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(0, 0), "Address", null, 0, null, Arrays.asList(new Review("1", "Author1", "Great food!", false)),null);
        repository.add(restaurant);

        Restaurant updatedRestaurant = new Restaurant("1", "Updated Restaurant", new Location(1, 1), "New Address", null, 0, null, Arrays.asList(new Review("1", "Author1", "Great food!", false)),null);
        boolean updated = repository.update(updatedRestaurant);
        assertTrue(updated, "Restaurant should be updated successfully");

        Restaurant retrieved = repository.getById("1").orElse(null);
        assertNotNull(retrieved, "Updated restaurant should be retrievable by ID");
        assertEquals("Updated Restaurant", retrieved.getName(), "Restaurant name should be updated");
        assertEquals(new Location(1, 1), retrieved.getLocation(), "Restaurant location should be updated");
    }

    @Test
    void testDeleteById() {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(0, 0), "Address", null, 0, null, Arrays.asList(new Review("1", "Author1", "Great food!", false)),null);
        repository.add(restaurant);
        boolean deleted = repository.deleteById("1");
        assertTrue(deleted, "Restaurant should be deleted successfully");
        assertFalse(repository.getById("1").isPresent(), "Restaurant should not be retrievable by ID after deletion");
    }

    @Test
    void testDeleteByName() {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(0, 0), "Address", null, 0, null, Arrays.asList(new Review("1", "Author1", "Great food!", false)),null);
        repository.add(restaurant);
        boolean deleted = repository.deleteByName("Test Restaurant");
        assertTrue(deleted, "Restaurant should be deleted successfully by name");
        assertFalse(repository.getByName("Test Restaurant").isPresent(), "Restaurant should not be retrievable by name after deletion");
    }
}
