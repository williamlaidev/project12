package main.java.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListTest {
    private RestaurantList restaurantList;

    @BeforeEach
    void setUp() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1, "Restaurant A", "Cuisine A", 4.5, 40.7128, -74.0060, "123 Main St", "http://example.com", "Great food!"));
        restaurants.add(new Restaurant(2, "Restaurant B", "Cuisine B", 4.0, 34.0522, -118.2437, "456 Elm St", "http://example.com", "Delicious dishes!"));

        restaurantList = new RestaurantList(1, 1001, "My Favorite Restaurants", "Restaurants I love", restaurants);
    }

    @AfterEach
    void tearDown() {
        // Perform any cleanup tasks if needed
        restaurantList = null;
    }

    @Test
    void testGetRestaurantListId() {
        assertEquals(1, restaurantList.getRestaurantListId());
    }

    @Test
    void testSetRestaurantListId() {
        restaurantList.setRestaurantListId(2);
        assertEquals(2, restaurantList.getRestaurantListId());
    }

    @Test
    void testGetUserId() {
        assertEquals(1001, restaurantList.getUserId());
    }

    @Test
    void testSetUserId() {
        restaurantList.setUserId(2002);
        assertEquals(2002, restaurantList.getUserId());
    }

    @Test
    public void testGetName() {
        assertEquals("My Favorite Restaurants", restaurantList.getName());
    }

    @Test
    void testSetName() {
        restaurantList.setName("New List Name");
        assertEquals("New List Name", restaurantList.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("Restaurants I love", restaurantList.getDescription());
    }

    @Test
    void testSetDescription() {
        restaurantList.setDescription("My updated favorite restaurants");
        assertEquals("My updated favorite restaurants", restaurantList.getDescription());
    }

    @Test
    void testGetRestaurants() {
        List<Restaurant> restaurants = restaurantList.getRestaurants();
        assertNotNull(restaurants);
        assertEquals(2, restaurants.size());
        assertEquals("Restaurant A", restaurants.get(0).getName());
        assertEquals("Restaurant B", restaurants.get(1).getName());
    }

    @Test
    void testSetRestaurants() {
        List<Restaurant> newRestaurants = new ArrayList<>();
        newRestaurants.add(new Restaurant(3, "Restaurant C", "Cuisine C", 4.2, 37.7749, -122.4194, "789 Oak St", "http://example.com", "Fantastic experience!"));
        newRestaurants.add(new Restaurant(4, "Restaurant D", "Cuisine D", 3.8, 41.8781, -87.6298, "101 Lake Shore Dr", "http://example.com", "Good food and service"));

        restaurantList.setRestaurants(newRestaurants);

        List<Restaurant> updatedRestaurants = restaurantList.getRestaurants();
        assertNotNull(updatedRestaurants);
        assertEquals(2, updatedRestaurants.size()); // Check size of new list
        assertEquals("Restaurant C", updatedRestaurants.get(0).getName()); // Check first restaurant in updated list
        assertEquals("Restaurant D", updatedRestaurants.get(1).getName()); // Check second restaurant in updated list
    }

    @Test
    void testToString() {
        String expectedString = "RestaurantList{restaurantListId=1, userId=1001, name='My Favorite Restaurants', description='Restaurants I love', restaurants=[Restaurant{restaurantId=1, name='Restaurant A', cuisineType='Cuisine A', averageRating=4.5, latitude=40.7128, longitude=-74.006, address='123 Main St', photoUrl='http://example.com', summarizedReview='Great food!'}, Restaurant{restaurantId=2, name='Restaurant B', cuisineType='Cuisine B', averageRating=4.0, latitude=34.0522, longitude=-118.2437, address='456 Elm St', photoUrl='http://example.com', summarizedReview='Delicious dishes!'}]}";
        assertEquals(expectedString, restaurantList.toString());
    }
}
