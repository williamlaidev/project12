package entity.restaurant;

import entity.DishType;
import entity.location.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantValidatorTest {

    // A simple concrete implementation for testing purposes
    static class AlwaysTrueValidator extends RestaurantValidator {
        @Override
        public boolean check(Restaurant restaurant) {
            return true;
        }
    }

    static class AlwaysFalseValidator extends RestaurantValidator {
        @Override
        public boolean check(Restaurant restaurant) {
            return false;
        }
    }

    @Test
    public void testValidatorChainPass() {
        RestaurantValidator validatorChain = new AlwaysTrueValidator();
        validatorChain.linkWith(new AlwaysTrueValidator())
                .linkWith(new AlwaysTrueValidator());

        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("valid_id", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");

        assertTrue(validatorChain.check(restaurant));
    }

    @Test
    public void testSingleValidatorPass() {
        RestaurantValidator validator = new AlwaysTrueValidator();

        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("valid_id", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");

        assertTrue(validator.check(restaurant));
    }

    @Test
    public void testSingleValidatorFail() {
        RestaurantValidator validator = new AlwaysFalseValidator();

        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant = new Restaurant("valid_id", "Valid Restaurant", location, "123 Main Street", DishType.ITALIAN, 4.5, "http://example.com/photo.jpg");

        assertFalse(validator.check(restaurant));
    }
}
