package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantListTest {

    private RestaurantList restaurantList;
    private User user;
    private List<Restaurant> restaurants;

    @BeforeEach
    public void setUp() {
        user = new CommonUser(1, "Kera Kim", new Location(43.6532, -79.3832), java.time.LocalDateTime.now());
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("ChIJqwg8BAA1K4gRGVOVcxx71Kw", "Italian Restaurant", new Location(43.6510, -79.3470), "123 Food Street, Toronto, ON", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg", new ArrayList<>(), null));
        restaurants.add(new Restaurant("LhIJqwg8CKW1K4gRGVOVcxx71Kx", "Chinese Restaurant", new Location(43.7000, -79.4000), "456 Yummy Avenue, Toronto, ON", DishType.CHINESE, 4.7, "http://example.com/photo.jpg", new ArrayList<>(), null));
    }

    @AfterEach
    public void tearDown() {
        restaurantList = null;
        user = null;
        restaurants = null;
    }

    @Test
    public void testValidRestaurantListCreation() {
        restaurantList = new RestaurantList(1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants);

        assertNotNull(restaurantList);
        assertEquals(1, restaurantList.getRestaurantListId());
        assertEquals(user, restaurantList.getUser());
        assertEquals("My Favorite Places", restaurantList.getName());
        assertEquals("A list of my favorite restaurants.", restaurantList.getDescription());
        assertEquals(restaurants, restaurantList.getRestaurants());
    }

    @Test
    public void testInvalidRestaurantListId() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new RestaurantList(-1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants));
        assertEquals("Restaurant list ID cannot be negative.", thrown.getMessage());
    }

    @Test
    public void testInvalidUser() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new RestaurantList(1, null, "My Favorite Places", "A list of my favorite restaurants.", restaurants));
        assertEquals("User cannot be null.", thrown.getMessage());
    }

    @Test
    public void testInvalidName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new RestaurantList(1, user, "", "A list of my favorite restaurants.", restaurants));
        assertEquals("Restaurant list name cannot be empty or null.", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> new RestaurantList(1, user, null, "A list of my favorite restaurants.", restaurants));
        assertEquals("Restaurant list name cannot be empty or null.", thrown.getMessage());
    }

    @Test
    public void testSetRestaurantListId() {
        restaurantList = new RestaurantList(1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants);
        restaurantList.setRestaurantListId(2);
        assertEquals(2, restaurantList.getRestaurantListId());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurantList.setRestaurantListId(-1));
        assertEquals("Restaurant list ID cannot be negative.", thrown.getMessage());
    }

    @Test
    public void testSetUser() {
        restaurantList = new RestaurantList(1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants);
        User newUser = new CommonUser(2, "Jenny", new Location(40.7128, -74.0060), java.time.LocalDateTime.now());
        restaurantList.setUser(newUser);
        assertEquals(newUser, restaurantList.getUser());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurantList.setUser(null));
        assertEquals("User cannot be null.", thrown.getMessage());
    }

    @Test
    public void testSetName() {
        restaurantList = new RestaurantList(1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants);
        restaurantList.setName("New Favorite Places");
        assertEquals("New Favorite Places", restaurantList.getName());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> restaurantList.setName(""));
        assertEquals("Restaurant list name cannot be empty or null.", thrown.getMessage());
    }

    @Test
    public void testSetDescription() {
        restaurantList = new RestaurantList(1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants);
        restaurantList.setDescription("Updated description");
        assertEquals("Updated description", restaurantList.getDescription());

        restaurantList.setDescription(null);
        assertEquals("", restaurantList.getDescription());
    }

    @Test
    public void testSetRestaurants() {
        restaurantList = new RestaurantList(1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants);
        List<Restaurant> newRestaurants = new ArrayList<>();
        newRestaurants.add(new Restaurant("ChIJqwg8BAA1K4gRGVOVcxx71Ky", "Japanese Restaurant", new Location(43.6600, -79.3500), "789 Tasty Blvd, Toronto, ON", DishType.JAPANESE, 4.8, "http://example.com/photo.jpg", new ArrayList<>(), null));
        restaurantList.setRestaurants(newRestaurants);
        assertEquals(newRestaurants, restaurantList.getRestaurants());
    }

    @Test
    public void testToString() {
        restaurantList = new RestaurantList(1, user, "My Favorite Places", "A list of my favorite restaurants.", restaurants);
        String expectedString = "RestaurantList{restaurantListId=1, user=" + user + ", name='My Favorite Places', description='A list of my favorite restaurants.', restaurants=" + restaurants + "}";
        assertEquals(expectedString, restaurantList.toString());
    }
}
