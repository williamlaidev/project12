package entity.map;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapFactoryTest {

    private final MapFactory mapFactory = new MapDefaultFactory();

    @Test
    public void testCreateMap() {
        List<Integer> restaurantIds = new ArrayList<>();
        restaurantIds.add(1);
        restaurantIds.add(2);
        restaurantIds.add(3);

        Map map = mapFactory.createMap(40.7128, -74.0060, 12, restaurantIds);

        assertNotNull(map);
        assertEquals(40.7128, map.getCurrentLatitude());
        assertEquals(-74.0060, map.getCurrentLongitude());
        assertEquals(12, map.getZoomLevel());
        assertEquals(restaurantIds, map.getDisplayedRestaurantIds());
    }

    @Test
    public void testCreateMapWithEmptyRestaurantIds() {
        List<Integer> restaurantIds = new ArrayList<>();

        Map map = mapFactory.createMap(40.7128, -74.0060, 12, restaurantIds);

        assertNotNull(map);
        assertEquals(40.7128, map.getCurrentLatitude());
        assertEquals(-74.0060, map.getCurrentLongitude());
        assertEquals(12, map.getZoomLevel());
        assertTrue(map.getDisplayedRestaurantIds().isEmpty());
    }

    @Test
    public void testCreateMapWithNullRestaurantIds() {
        Map map = mapFactory.createMap(40.7128, -74.0060, 12, null);

        assertNotNull(map);
        assertEquals(40.7128, map.getCurrentLatitude());
        assertEquals(-74.0060, map.getCurrentLongitude());
        assertEquals(12, map.getZoomLevel());
        assertNull(map.getDisplayedRestaurantIds());
    }
}
