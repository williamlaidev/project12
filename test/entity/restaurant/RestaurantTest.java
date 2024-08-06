package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    @Test
    public void testValidRestaurantCreation() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        assertNotNull(restaurant);
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", restaurant.getRestaurantId());
        assertEquals("Valid Restaurant", restaurant.getName());
        assertEquals(location, restaurant.getLocation());
        assertEquals("123 Main Street", restaurant.getAddress());
        assertEquals(DishType.ITALIAN, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }

    @Test
    public void testInvalidRestaurantName() {
        Location location = new Location(40.7128, -74.0060);
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
    }

    @Test
    public void testInvalidRestaurantAddress() {
        Location location = new Location(40.7128, -74.0060);
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
    }

    @Test
    public void testInvalidAverageRating() {
        Location location = new Location(40.7128, -74.0060);
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 6.0, "http://example.com/photo.jpg");
        });
    }

    @Test
    public void testSetName() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        restaurant.setName("New Name");
        assertEquals("New Name", restaurant.getName());
        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setName("");
        });
    }

    @Test
    public void testSetAddress() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        restaurant.setAddress("456 Another Street");
        assertEquals("456 Another Street", restaurant.getAddress());
        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setAddress("");
        });
    }

    @Test
    public void testSetAverageRating() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        restaurant.setAverageRating(3.5);
        assertEquals(3.5, restaurant.getAverageRating());
        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setAverageRating(6.0);
        });
    }

    @Test
    public void testSetDishType() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        restaurant.setDishType(DishType.CHINESE);
        assertEquals(DishType.CHINESE, restaurant.getDishType());
    }

    @Test
    public void testSetLocation() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        Location newLocation = new Location(37.7749, -122.4194);
        restaurant.setLocation(newLocation);
        assertEquals(newLocation, restaurant.getLocation());
    }

    @Test
    public void testSetPhotoUrl() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        restaurant.setPhotoUrl("http://newphoto.com/photo.jpg");
        assertEquals("http://newphoto.com/photo.jpg", restaurant.getPhotoUrl());
        restaurant.setPhotoUrl(null);
        assertEquals("", restaurant.getPhotoUrl());
    }

    @Test
    public void testEquals() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant1 = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        Restaurant restaurant2 = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        Restaurant restaurant3 = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Another Restaurant", location, "456 Another Street", DishType.CHINESE, 3.5, "http://example.com/photo2.jpg");

        assertEquals(restaurant1, restaurant2);
        assertNotEquals(restaurant1, restaurant3);
        assertNotEquals(restaurant1, null);
        assertNotEquals(restaurant1, new Object());
    }

    @Test
    public void testSetNullName() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setName(null);
        });
    }

    @Test
    public void testSetNullAddress() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setAddress(null);
        });
    }

    @Test
    public void testSetNegativeAverageRating() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        assertThrows(IllegalArgumentException.class, () -> {
            restaurant.setAverageRating(-1.0);
        });
    }

    @Test
    public void testSetPhotoUrlEmptyString() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        restaurant.setPhotoUrl("");
        assertEquals("", restaurant.getPhotoUrl());
    }
    @Test
    public void testNullDishType() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", null, 4.5, "http://example.com/photo.jpg");
        assertNull(restaurant.getDishType());
    }

    @Test
    public void testEmptyRestaurantId() {
        Location location = new Location(40.7128, -74.0060);
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
    }

    @Test
    public void testToString() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        String expectedString = "Restaurant{restaurantId='ChIJN1t_tDeuEmsRUsoyG83frY4', name='Valid Restaurant', location=Location{latitude=40.7128, longitude=-74.006}, address='123 Main Street', dishType=ITALIAN, averageRating=4.5, photoUrl='http://example.com/photo.jpg'}";
        String actualString = restaurant.toString();
        assertEquals(expectedString, actualString);
    }
    @Test
    public void testSameRestaurantId() {
        Location location1 = new Location(40.7128, -74.0060);
        Location location2 = new Location(34.0522, -118.2437);
        Restaurant restaurant1 = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Restaurant 1", location1, "123 Main Street", DishType.ITALIAN, 4.0, "http://example.com/photo1.jpg");
        Restaurant restaurant2 = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Restaurant 2", location2, "456 Another Street", DishType.CHINESE, 3.5, "http://example.com/photo2.jpg");

        assertEquals(restaurant1.getRestaurantId(), restaurant2.getRestaurantId());
        assertNotEquals(restaurant1, restaurant2);
    }

    @Test
    public void testDifferentRestaurantId() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant1 = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Restaurant 1", location, "123 Main Street", DishType.ITALIAN, 4.0, "http://example.com/photo1.jpg");
        Restaurant restaurant2 = new Restaurant("C3JKE0134NRUso35yG83frY49LP", "Restaurant 2", location, "123 Main Street", DishType.ITALIAN, 4.0, "http://example.com/photo2.jpg");

        assertNotEquals(restaurant1.getRestaurantId(), restaurant2.getRestaurantId());
        assertNotEquals(restaurant1, restaurant2);
    }

    @Test
    public void testDifferentHashCode() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant1 = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Restaurant 1", location, "123 Main Street", DishType.ITALIAN, 4.0, "http://example.com/photo1.jpg");
        Restaurant restaurant2 = new Restaurant("ChIJN1t_tDeuEmsRTKDPSf2431I", "Restaurant 1", location, "123 Main Street", DishType.ITALIAN, 4.0, "http://example.com/photo1.jpg");

        assertNotEquals(restaurant1.hashCode(), restaurant2.hashCode());
    }


}
