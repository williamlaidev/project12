package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void testLocationConstructorAndGetters() {
        double latitude = 40.7128;
        double longitude = -74.0060;

        Location location = new Location(latitude, longitude);

        assertEquals(latitude, location.getLatitude());
        assertEquals(longitude, location.getLongitude());
    }

    @Test
    void testSetLatitudeAndLongitude() {
        Location location = new Location(40.7128, -74.0060);

        location.setLatitude(34.0522);
        location.setLongitude(-118.2437);

        assertEquals(34.0522, location.getLatitude());
        assertEquals(-118.2437, location.getLongitude());
    }

    @Test
    void testResetLocation() {
        Location location = new Location(40.7128, -74.0060);

        location.resetLocation(37.7749, -122.4194);

        assertEquals(37.7749, location.getLatitude());
        assertEquals(-122.4194, location.getLongitude());
    }

    @Test
    void testToString() {
        Location location = new Location(40.7128, -74.0060);

        String expectedString = "Location{latitude=40.7128, longitude=-74.006}";
        assertEquals(expectedString, location.toString());
    }

    @Test
    void testEquals() {
        Location location1 = new Location(40.7128, -74.0060);
        Location location2 = new Location(40.7128, -74.0060);
        Location location3 = new Location(34.0522, -118.2437);

        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
    }

    @Test
    void testInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> new Location(-91, 0));
        assertThrows(IllegalArgumentException.class, () -> new Location(91, 0));
    }

    @Test
    void testInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> new Location(0, -181));
        assertThrows(IllegalArgumentException.class, () -> new Location(0, 181));
    }
}
