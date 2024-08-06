package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantAddressValidatorTest {

    @Test
    public void testValidAddress() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        RestaurantValidator validator = new RestaurantAddressValidator();
        assertTrue(validator.check(restaurant));
    }

    @Test
    public void testInvalidAddressNull() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantValidator validator = new RestaurantAddressValidator();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, null, DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", thrown.getMessage());
    }

    @Test
    public void testInvalidAddressEmpty() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantValidator validator = new RestaurantAddressValidator();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", thrown.getMessage());
    }

    @Test
    public void testInvalidAddressWhitespace() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantValidator validator = new RestaurantAddressValidator();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "   ", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", thrown.getMessage());
    }
}
