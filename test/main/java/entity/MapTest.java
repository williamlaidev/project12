package main.java.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Map map;
    private List<Integer> displayedRestaurantIds;

    @BeforeEach
    void setUp() {
        displayedRestaurantIds = Arrays.asList(101, 102, 103);
        map = new Map(40.7128, -74.0060, 12, displayedRestaurantIds);
    }

    @AfterEach
    void tearDown() {
        map = null;
        displayedRestaurantIds = null;
    }

    @Test
    void getCurrentLatitude() {
        assertEquals(40.7128, map.getCurrentLatitude());
    }

    @Test
    void getCurrentLongitude() {
        assertEquals(-74.0060, map.getCurrentLongitude());
    }

    @Test
    void getZoomLevel() {
        assertEquals(12, map.getZoomLevel());
    }

    @Test
    void getDisplayedRestaurantIds() {
        assertEquals(displayedRestaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    void setCurrentLatitude() {
        map.setCurrentLatitude(34.0522);
        assertEquals(34.0522, map.getCurrentLatitude());
    }

    @Test
    void setCurrentLongitude() {
        map.setCurrentLongitude(-118.2437);
        assertEquals(-118.2437, map.getCurrentLongitude());
    }

    @Test
    void setZoomLevel() {
        map.setZoomLevel(15);
        assertEquals(15, map.getZoomLevel());
    }

    @Test
    void setDisplayedRestaurantIds() {
        List<Integer> newDisplayedIds = Arrays.asList(201, 202, 203);
        map.setDisplayedRestaurantIds(newDisplayedIds);
        assertEquals(newDisplayedIds, map.getDisplayedRestaurantIds());
    }

    @Test
    void testToString() {
        String expected = "Map{currentLatitude=40.7128, currentLongitude=-74.006, zoomLevel=12, displayedRestaurantIds=[101, 102, 103]}";
        assertEquals(expected, map.toString());
    }
}
