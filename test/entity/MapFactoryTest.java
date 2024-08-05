package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapFactoryTest {

    private List<Integer> displayedRestaurantIds;

    @BeforeEach
    public void setUp() {
        displayedRestaurantIds = new ArrayList<>();
        displayedRestaurantIds.add(101);
        displayedRestaurantIds.add(102);
        displayedRestaurantIds.add(103);
    }

    @AfterEach
    public void tearDown() {
        displayedRestaurantIds = null;
    }

    @Test
    public void testCreateMap() {
        Map map = MapFactory.createMap(49.2827, -123.1207, 15, displayedRestaurantIds);

        assertNotNull(map);
        assertEquals(49.2827, map.getCurrentLatitude());
        assertEquals(-123.1207, map.getCurrentLongitude());
        assertEquals(15, map.getZoomLevel());
        assertEquals(displayedRestaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    public void testCreateMapWithDifferentCoordinates() {
        Map map = MapFactory.createMap(48.4284, -123.3656, 10, displayedRestaurantIds);

        assertNotNull(map);
        assertEquals(48.4284, map.getCurrentLatitude());
        assertEquals(-123.3656, map.getCurrentLongitude());
        assertEquals(10, map.getZoomLevel());
        assertEquals(displayedRestaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    public void testCreateMapWithEmptyDisplayedRestaurantIds() {
        List<Integer> emptyDisplayedRestaurantIds = new ArrayList<>();
        Map map = MapFactory.createMap(47.6062, -122.3321, 12, emptyDisplayedRestaurantIds);

        assertNotNull(map);
        assertEquals(47.6062, map.getCurrentLatitude());
        assertEquals(-122.3321, map.getCurrentLongitude());
        assertEquals(12, map.getZoomLevel());
        assertTrue(map.getDisplayedRestaurantIds().isEmpty());
    }

    @Test
    public void testCreateMapWithNullDisplayedRestaurantIds() {
        Map map = MapFactory.createMap(49.8951, -97.1384, 18, null);

        assertNotNull(map);
        assertEquals(49.8951, map.getCurrentLatitude());
        assertEquals(-97.1384, map.getCurrentLongitude());
        assertEquals(18, map.getZoomLevel());
        assertNull(map.getDisplayedRestaurantIds());
    }
}
