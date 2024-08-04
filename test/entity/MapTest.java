package entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void testMapConstructorAndGetters() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        int zoomLevel = 10;
        List<Integer> restaurantIds = Arrays.asList(1, 2, 3);

        Map map = new Map(latitude, longitude, zoomLevel, restaurantIds);

        assertEquals(latitude, map.getCurrentLatitude());
        assertEquals(longitude, map.getCurrentLongitude());
        assertEquals(zoomLevel, map.getZoomLevel());
        assertEquals(restaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    void testMapSetters() {
        Map map = new Map(40.7128, -74.0060, 10, Arrays.asList(1, 2, 3));

        map.setCurrentLatitude(34.0522);
        map.setCurrentLongitude(-118.2437);
        map.setZoomLevel(12);
        map.setDisplayedRestaurantIds(Collections.singletonList(4));

        assertEquals(34.0522, map.getCurrentLatitude());
        assertEquals(-118.2437, map.getCurrentLongitude());
        assertEquals(12, map.getZoomLevel());
        assertEquals(Collections.singletonList(4), map.getDisplayedRestaurantIds());
    }

    @Test
    void testMapToString() {
        List<Integer> restaurantIds = Arrays.asList(1, 2, 3);
        Map map = new Map(40.7128, -74.0060, 10, restaurantIds);

        String expectedString = "Map{currentLatitude=40.7128, currentLongitude=-74.006, zoomLevel=10, displayedRestaurantIds=[1, 2, 3]}";
        assertEquals(expectedString, map.toString());
    }
}
