package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(37.7749, -122.4194);
    }

    @AfterEach
    void tearDown() {
        location = null;
    }

    @Test
    void testConstructorValid() {
        assertNotNull(location);
        assertEquals(37.7749, location.getLatitude());
        assertEquals(-122.4194, location.getLongitude());
    }

    @Test
    void testConstructorInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(-91.0, -122.4194);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(91.0, -122.4194);
        });
    }

    @Test
    void testConstructorInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(37.7749, -181.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(37.7749, 181.0);
        });
    }

    @Test
    void testGetLatitude() {
        assertEquals(37.7749, location.getLatitude());
    }

    @Test
    void testSetLatitude() {
        location.setLatitude(40.7128);
        assertEquals(40.7128, location.getLatitude());
    }

    @Test
    void testSetLatitudeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            location.setLatitude(-91.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            location.setLatitude(91.0);
        });
    }

    @Test
    void testGetLongitude() {
        assertEquals(-122.4194, location.getLongitude());
    }

    @Test
    void testSetLongitude() {
        location.setLongitude(-74.0060);
        assertEquals(-74.0060, location.getLongitude());
    }

    @Test
    void testSetLongitudeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            location.setLongitude(-181.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            location.setLongitude(181.0);
        });
    }

    @Test
    void testResetLocation() {
        location.resetLocation(34.0522, -118.2437);
        assertEquals(34.0522, location.getLatitude());
        assertEquals(-118.2437, location.getLongitude());
    }

    @Test
    void testResetLocationInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> {
            location.resetLocation(-91.0, -118.2437);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            location.resetLocation(91.0, -118.2437);
        });
    }

    @Test
    void testResetLocationInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> {
            location.resetLocation(34.0522, -181.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            location.resetLocation(34.0522, 181.0);
        });
    }

    @Test
    void testToString() {
        String expectedString = "Location{latitude=37.7749, longitude=-122.4194}";
        assertEquals(expectedString, location.toString());
    }

    @Test
    void testEquals() {
        Location location1 = new Location(37.7749, -122.4194);
        Location location2 = new Location(37.7749, -122.4194);
        Location location3 = new Location(40.7128, -74.0060);

        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
        assertNotEquals(location1, null);
        assertNotEquals(location1, new Object());
    }

    @Test
    void testHashCode() {
        Location location1 = new Location(37.7749, -122.4194);
        Location location2 = new Location(37.7749, -122.4194);
        Location location3 = new Location(40.7128, -74.0060);

        assertEquals(location1.hashCode(), location2.hashCode());
        assertNotEquals(location1.hashCode(), location3.hashCode());
    }
}
