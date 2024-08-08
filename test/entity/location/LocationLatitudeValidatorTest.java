package entity.location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationLatitudeValidatorTest {

    private final LocationValidator validator = new LocationLatitudeValidator();

    @Test
    public void testValidLatitude() {
        Location location = new Location(37.7749, -122.4194);
        assertTrue(validator.check(location));
    }

    @Test
    public void testInvalidLatitudeLow() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Location(-91.0, -122.4194);
        });
        assertEquals("Invalid latitude or longitude.", thrown.getMessage());
    }

    @Test
    public void testInvalidLatitudeHigh() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Location(91.0, -122.4194);
        });
        assertEquals("Invalid latitude or longitude.", thrown.getMessage());
    }
}
