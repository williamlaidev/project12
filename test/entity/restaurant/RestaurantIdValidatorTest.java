package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantIdValidatorTest {

    private final RestaurantValidator validator = new RestaurantIdValidator();

    @Test
    public void testValidRestaurantId() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        assertTrue(validator.check(restaurant));
    }

    @Test
    public void testInvalidRestaurantIdNull() {
        Location location = new Location(40.7128, -74.0060);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant(null, "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", exception.getMessage());
    }

    @Test
    public void testInvalidRestaurantIdEmpty() {
        Location location = new Location(40.7128, -74.0060);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", exception.getMessage());
    }

    @Test
    public void testInvalidRestaurantIdWhitespace() {
        Location location = new Location(40.7128, -74.0060);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("   ", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", exception.getMessage());
    }
}
