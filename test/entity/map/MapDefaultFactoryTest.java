package entity.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapDefaultFactoryTest {

    @Test
    public void testCreateMap() {
        MapFactory factory = new MapDefaultFactory();
        Map map = factory.createMap(37.7749, -122.4194, 10, 800, 600);

        assertNotNull(map);
        assertEquals(37.7749, map.getCurrentLatitude());
        assertEquals(-122.4194, map.getCurrentLongitude());
        assertEquals(10, map.getZoomLevel());
        assertEquals(800, map.getWidth());
        assertEquals(600, map.getHeight());
    }
}
