package entity.map;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    @Test
    public void testMapConstructor() {
        List<Integer> restaurantIds = new ArrayList<>();
        restaurantIds.add(1);
        restaurantIds.add(2);
        restaurantIds.add(3);

        Map map = new Map(40.7128, -74.0060, 12, restaurantIds);

        assertEquals(40.7128, map.getCurrentLatitude());
        assertEquals(-74.0060, map.getCurrentLongitude());
        assertEquals(12, map.getZoomLevel());
        assertEquals(restaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    public void testSetCurrentLatitude() {
        Map map = new Map(0, 0, 10, new ArrayList<>());
        map.setCurrentLatitude(37.7749);

        assertEquals(37.7749, map.getCurrentLatitude());
    }

    @Test
    public void testSetCurrentLongitude() {
        Map map = new Map(0, 0, 10, new ArrayList<>());
        map.setCurrentLongitude(-122.4194);

        assertEquals(-122.4194, map.getCurrentLongitude());
    }

    @Test
    public void testSetZoomLevel() {
        Map map = new Map(0, 0, 10, new ArrayList<>());
        map.setZoomLevel(15);

        assertEquals(15, map.getZoomLevel());
    }

    @Test
    public void testSetDisplayedRestaurantIds() {
        Map map = new Map(0, 0, 10, new ArrayList<>());
        List<Integer> restaurantIds = new ArrayList<>();
        restaurantIds.add(1);
        restaurantIds.add(2);

        map.setDisplayedRestaurantIds(restaurantIds);

        assertEquals(restaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    public void testToString() {
        List<Integer> restaurantIds = new ArrayList<>();
        restaurantIds.add(1);
        restaurantIds.add(2);

        Map map = new Map(37.7749, -122.4194, 10, restaurantIds);
        String expectedString = "Map{currentLatitude=37.7749, currentLongitude=-122.4194, zoomLevel=10, displayedRestaurantIds=[1, 2]}";

        assertEquals(expectedString, map.toString());
    }
}
