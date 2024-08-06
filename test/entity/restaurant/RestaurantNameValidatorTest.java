package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantNameValidatorTest {

    private final RestaurantValidator validator = new RestaurantNameValidator();

    @Test
    public void testValidRestaurantName() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        assertTrue(validator.check(restaurant));
    }

    @Test
    public void testInvalidRestaurantNameNull() {
        Location location = new Location(40.7128, -74.0060);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", null, location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", exception.getMessage());
    }

    @Test
    public void testInvalidRestaurantNameEmpty() {
        Location location = new Location(40.7128, -74.0060);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", exception.getMessage());
    }

    @Test
    public void testInvalidRestaurantNameWhitespace() {
        Location location = new Location(40.7128, -74.0060);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "   ", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", exception.getMessage());
    }
}
