package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantAverageRatingValidatorTest {

    private final RestaurantValidator validator = new RestaurantAverageRatingValidator();

    @Test
    public void testValidAverageRating() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");
        assertTrue(validator.check(restaurant));
    }

    @Test
    public void testInvalidAverageRatingLow() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantValidator validator = new RestaurantAverageRatingValidator();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, -1, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", thrown.getMessage());
    }

    @Test
    public void testInvalidAverageRatingHigh() {
        Location location = new Location(40.7128, -74.0060);
        RestaurantValidator validator = new RestaurantAverageRatingValidator();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 6, "http://example.com/photo.jpg");
        });
        assertEquals("Invalid restaurant data.", thrown.getMessage());
    }
}
