package entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMapFactoryTest {

    @Test
    void testCreateMap() {
        MapFactory factory = new DefaultMapFactory();
        double latitude = 40.7128;
        double longitude = -74.0060;
        int zoomLevel = 10;
        List<Integer> restaurantIds = Arrays.asList(1, 2, 3);

        Map map = factory.createMap(latitude, longitude, zoomLevel, restaurantIds);

        assertEquals(latitude, map.getCurrentLatitude());
        assertEquals(longitude, map.getCurrentLongitude());
        assertEquals(zoomLevel, map.getZoomLevel());
        assertEquals(restaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    void testCreateMapWithEmptyRestaurantIds() {
        MapFactory factory = new DefaultMapFactory();
        double latitude = 40.7128;
        double longitude = -74.0060;
        int zoomLevel = 10;
        List<Integer> restaurantIds = Collections.emptyList();

        Map map = factory.createMap(latitude, longitude, zoomLevel, restaurantIds);

        assertEquals(latitude, map.getCurrentLatitude());
        assertEquals(longitude, map.getCurrentLongitude());
        assertEquals(zoomLevel, map.getZoomLevel());
        assertTrue(map.getDisplayedRestaurantIds().isEmpty());
    }
}
