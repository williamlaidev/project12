package test.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.entity.Restaurant;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant(1, "Example Restaurant", "Italian", 4.5,
                40.7128, -74.0060, "123 Example St, New York, NY",
                "http://example.com/photo.jpg", "Great food and ambiance.");
    }

    @Test
    public void testGetters() {
        assertEquals(1, restaurant.getRestaurantId());
        assertEquals("Example Restaurant", restaurant.getName());
        assertEquals("Italian", restaurant.getCuisineType());
        assertEquals(4.5, restaurant.getAverageRating(), 0.001);
        assertEquals(40.7128, restaurant.getLatitude(), 0.001);
        assertEquals(-74.0060, restaurant.getLongitude(), 0.001);
        assertEquals("123 Example St, New York, NY", restaurant.getAddress());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
        assertEquals("Great food and ambiance.", restaurant.getSummarizedReview());
    }

    @Test
    public void testSetters() {
        restaurant.setRestaurantId(2);
        restaurant.setName("New Restaurant Name");
        restaurant.setCuisineType("French");
        restaurant.setAverageRating(4.2);
        restaurant.setLatitude(38.8951);
        restaurant.setLongitude(-77.0367);
        restaurant.setAddress("456 New St, Washington, DC");
        restaurant.setPhotoUrl("http://newexample.com/photo.jpg");
        restaurant.setSummarizedReview("Delicious French cuisine.");

        assertEquals(2, restaurant.getRestaurantId());
        assertEquals("New Restaurant Name", restaurant.getName());
        assertEquals("French", restaurant.getCuisineType());
        assertEquals(4.2, restaurant.getAverageRating(), 0.001);
        assertEquals(38.8951, restaurant.getLatitude(), 0.001);
        assertEquals(-77.0367, restaurant.getLongitude(), 0.001);
        assertEquals("456 New St, Washington, DC", restaurant.getAddress());
        assertEquals("http://newexample.com/photo.jpg", restaurant.getPhotoUrl());
        assertEquals("Delicious French cuisine.", restaurant.getSummarizedReview());
    }

    @Test
    public void testToString() {
        String expectedString = "Restaurant{" +
                "restaurantId=1, " +
                "name='Example Restaurant', " +
                "cuisineType='Italian', " +
                "averageRating=4.5, " +
                "latitude=40.7128, " +
                "longitude=-74.006, " +
                "address='123 Example St, New York, NY', " +
                "photoUrl='http://example.com/photo.jpg', " +
                "summarizedReview='Great food and ambiance.'}";

        assertEquals(expectedString, restaurant.toString());
    }
}