package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    private Map map;
    private List<Integer> restaurantIds;

    @BeforeEach
    public void setUp() {
        restaurantIds = new ArrayList<>();
        restaurantIds.add(101);
        restaurantIds.add(102);
        restaurantIds.add(103);

        map = new Map(43.6532, -79.3832, 15, restaurantIds);
    }

    @AfterEach
    public void tearDown() {
        map = null;
        restaurantIds = null;
    }

    @Test
    public void testMapCreation() {
        assertNotNull(map);
        assertEquals(43.6532, map.getCurrentLatitude());
        assertEquals(-79.3832, map.getCurrentLongitude());
        assertEquals(15, map.getZoomLevel());
        assertEquals(restaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    public void testSetCurrentLatitude() {
        map.setCurrentLatitude(40.7128);
        assertEquals(40.7128, map.getCurrentLatitude());
    }

    @Test
    public void testSetCurrentLongitude() {
        map.setCurrentLongitude(-74.0060);
        assertEquals(-74.0060, map.getCurrentLongitude());
    }

    @Test
    public void testSetZoomLevel() {
        map.setZoomLevel(10);
        assertEquals(10, map.getZoomLevel());
    }

    @Test
    public void testSetDisplayedRestaurantIds() {
        List<Integer> newRestaurantIds = new ArrayList<>();
        newRestaurantIds.add(201);
        newRestaurantIds.add(202);
        newRestaurantIds.add(203);

        map.setDisplayedRestaurantIds(newRestaurantIds);
        assertEquals(newRestaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    public void testToString() {
        String expectedString = "Map{currentLatitude=43.6532, currentLongitude=-79.3832, zoomLevel=15, displayedRestaurantIds=[101, 102, 103]}";
        assertEquals(expectedString, map.toString());
    }
}
