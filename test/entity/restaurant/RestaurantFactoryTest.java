package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantFactoryTest {

    @Test
    public void testCreateRestaurant() {
        RestaurantFactory factory = new RestaurantDefaultFactory();
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = factory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", location, "123 Main Street", DishType.KOREAN, 4.5, "http://example.com/photo.jpg");

        assertNotNull(restaurant);
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", restaurant.getRestaurantId());
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Main Street", restaurant.getAddress());
        assertEquals(DishType.KOREAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }
}
