package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultLocationFactoryTest {

    @Test
    void testCreateLocation() {
        LocationFactory factory = new DefaultLocationFactory();
        double latitude = 40.7128;
        double longitude = -74.0060;

        Location location = factory.createLocation(latitude, longitude);

        assertEquals(latitude, location.getLatitude());
        assertEquals(longitude, location.getLongitude());
    }

    @Test
    void testCreateLocationWithInvalidParameters() {
        LocationFactory factory = new DefaultLocationFactory();

        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(-91, 0));
        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(91, 0));
        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(0, -181));
        assertThrows(IllegalArgumentException.class, () -> factory.createLocation(0, 181));
    }
}
