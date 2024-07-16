package main.java.entity;

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
    void getLatitude() {
        assertEquals(37.7749, location.getLatitude());
    }

    @Test
    void setLatitude() {
        location.setLatitude(40.7128);
        assertEquals(40.7128, location.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(-122.4194, location.getLongitude());
    }

    @Test
    void setLongitude() {
        location.setLongitude(-74.0060);
        assertEquals(-74.0060, location.getLongitude());
    }

    @Test
    void resetLocation() {
        location.resetLocation(34.0522, -118.2437);
        assertEquals(34.0522, location.getLatitude());
        assertEquals(-118.2437, location.getLongitude());
    }

    @Test
    void testToString() {
        String expectedString = "Location{latitude=37.7749, longitude=-122.4194}";
        assertEquals(expectedString, location.toString());
    }
}
