package entity.location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationDefaultFactoryTest {

    private final LocationFactory factory = new LocationDefaultFactory();

    @Test
    public void testCreateLocationValid() {
        Location location = factory.createLocation(37.7749, -122.4194);
        assertNotNull(location);
        assertEquals(37.7749, location.getLatitude());
        assertEquals(-122.4194, location.getLongitude());
    }

    @Test
    public void testCreateLocationInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(-91.0, -122.4194));
        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(91.0, -122.4194));
    }

    @Test
    public void testCreateLocationInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(37.7749, -181.0));
        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(37.7749, 181.0));
    }
}
