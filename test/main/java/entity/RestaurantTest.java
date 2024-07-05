package main.java.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant(1, "Sample Restaurant", "Italian", 4.5,
                40.7128, -74.0060, "123 Main St, City, Country", "http://example.com/photo.jpg",
                "A great Italian restaurant with delicious pasta dishes.");
    }

    @AfterEach
    void tearDown() {
        restaurant = null;
    }

    @Test
    void getRestaurantId() {
        assertEquals(1, restaurant.getRestaurantId());
    }

    @Test
    void getName() {
        assertEquals("Sample Restaurant", restaurant.getName());
    }

    @Test
    void getCuisineType() {
        assertEquals("Italian", restaurant.getCuisineType());
    }

    @Test
    void getAverageRating() {
        assertEquals(4.5, restaurant.getAverageRating());
    }

    @Test
    void getLatitude() {
        assertEquals(40.7128, restaurant.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(-74.0060, restaurant.getLongitude());
    }

    @Test
    void getAddress() {
        assertEquals("123 Main St, City, Country", restaurant.getAddress());
    }

    @Test
    void getPhotoUrl() {
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }

    @Test
    void getSummarizedReview() {
        assertEquals("A great Italian restaurant with delicious pasta dishes.", restaurant.getSummarizedReview());
    }

    @Test
    void setRestaurantId() {
        restaurant.setRestaurantId(2);
        assertEquals(2, restaurant.getRestaurantId());
    }

    @Test
    void setName() {
        restaurant.setName("New Restaurant");
        assertEquals("New Restaurant", restaurant.getName());
    }

    @Test
    void setCuisineType() {
        restaurant.setCuisineType("French");
        assertEquals("French", restaurant.getCuisineType());
    }

    @Test
    void setAverageRating() {
        restaurant.setAverageRating(4.2);
        assertEquals(4.2, restaurant.getAverageRating());
    }

    @Test
    void setLatitude() {
        restaurant.setLatitude(34.0522);
        assertEquals(34.0522, restaurant.getLatitude());
    }

    @Test
    void setLongitude() {
        restaurant.setLongitude(-118.2437);
        assertEquals(-118.2437, restaurant.getLongitude());
    }

    @Test
    void setAddress() {
        restaurant.setAddress("456 Elm St, Another City, Another Country");
        assertEquals("456 Elm St, Another City, Another Country", restaurant.getAddress());
    }

    @Test
    void setPhotoUrl() {
        restaurant.setPhotoUrl("http://example.com/new_photo.jpg");
        assertEquals("http://example.com/new_photo.jpg", restaurant.getPhotoUrl());
    }

    @Test
    void setSummarizedReview() {
        restaurant.setSummarizedReview("An updated review for the restaurant.");
        assertEquals("An updated review for the restaurant.", restaurant.getSummarizedReview());
    }

    @Test
    void testToString() {
        String expected = "Restaurant{" +
                "restaurantId=1, " +
                "name='Sample Restaurant', " +
                "cuisineType='Italian', " +
                "averageRating=4.5, " +
                "latitude=40.7128, " +
                "longitude=-74.006, " +
                "address='123 Main St, City, Country', " +
                "photoUrl='http://example.com/photo.jpg', " +
                "summarizedReview='A great Italian restaurant with delicious pasta dishes.'}";
        assertEquals(expected, restaurant.toString());
    }
}
