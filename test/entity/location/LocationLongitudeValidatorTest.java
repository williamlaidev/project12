package entity.location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationLongitudeValidatorTest {

    private final LocationValidator validator = new LocationLongitudeValidator();

    @Test
    public void testValidLongitude() {
        Location location = new Location(37.7749, -122.4194);
        assertTrue(validator.check(location));
    }

    @Test
    public void testInvalidLongitudeLow() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Location(37.7749, -181.0);
        });
        assertEquals("Invalid latitude or longitude.", thrown.getMessage());
    }

    @Test
    public void testInvalidLongitudeHigh() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Location(37.7749, 181.0);
        });
        assertEquals("Invalid latitude or longitude.", thrown.getMessage());
    }
}
