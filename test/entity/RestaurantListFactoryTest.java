package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantListFactoryTest {

    private RestaurantListFactory factory;
    private User user;
    private List<Restaurant> restaurants;

    @BeforeEach
    public void setUp() {
        factory = new RestaurantListFactory();
        user = new CommonUser(1, "Kera Kim", new Location(43.6532, -79.3832), java.time.LocalDateTime.now());
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Italian Restaurant", new Location(43.6510, -79.3470), "123 Pizza Street, Toronto, ON", DishType.ITALIAN, 4.5, "http://example.com/photo1.jpg", new ArrayList<>(), null));
        restaurants.add(new Restaurant("LhIJqwg8CKW1K4gRGVOVcxx71Kx", "Chinese Restaurant", new Location(43.7000, -79.4000), "456 Noodle Avenue, Toronto, ON", DishType.CHINESE, 4.7, "http://example.com/photo2.jpg", new ArrayList<>(), null));
    }

    @AfterEach
    public void tearDown() {
        factory = null;
        user = null;
        restaurants = null;
    }

    @Test
    public void testCreateRestaurantList() {
        RestaurantList restaurantList = factory.createRestaurantList(
                1,
                user,
                "My Favorite Places",
                "A list of my favorite restaurants.",
                restaurants
        );

        assertNotNull(restaurantList);
        assertEquals(1, restaurantList.getRestaurantListId());
        assertEquals(user, restaurantList.getUser());
        assertEquals("My Favorite Places", restaurantList.getName());
        assertEquals("A list of my favorite restaurants.", restaurantList.getDescription());
        assertEquals(restaurants, restaurantList.getRestaurants());
    }

    @Test
    public void testCreateRestaurantListWithNullDescription() {
        RestaurantList restaurantList = factory.createRestaurantList(
                1,
                user,
                "My Favorite Places",
                null,
                restaurants
        );

        assertNotNull(restaurantList);
        assertEquals(1, restaurantList.getRestaurantListId());
        assertEquals(user, restaurantList.getUser());
        assertEquals("My Favorite Places", restaurantList.getName());
        assertEquals("", restaurantList.getDescription());
        assertEquals(restaurants, restaurantList.getRestaurants());
    }


    @Test
    public void testCreateRestaurantListInvalidId() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurantList(
                    -1,
                    user,
                    "My Favorite Places",
                    "A list of my favorite restaurants.",
                    restaurants
            );
        });
        assertEquals("Restaurant list ID cannot be negative.", thrown.getMessage());
    }

    @Test
    public void testCreateRestaurantListInvalidUser() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurantList(
                    1,
                    null,
                    "My Favorite Places",
                    "A list of my favorite restaurants.",
                    restaurants
            );
        });
        assertEquals("User cannot be null.", thrown.getMessage());
    }

    @Test
    public void testCreateRestaurantListInvalidName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurantList(
                    1,
                    user,
                    "",
                    "A list of my favorite restaurants.",
                    restaurants
            );
        });
        assertEquals("Restaurant list name cannot be empty or null.", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> {
            factory.createRestaurantList(
                    1,
                    user,
                    null,
                    "A list of my favorite restaurants.",
                    restaurants
            );
        });
        assertEquals("Restaurant list name cannot be empty or null.", thrown.getMessage());
    }
}
