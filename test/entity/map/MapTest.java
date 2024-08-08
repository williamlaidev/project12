package entity.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    @Test
    public void testMapConstructor() {
        Map map = new Map(37.7749, -122.4194, 10, 800, 600);

        assertEquals(37.7749, map.getCurrentLatitude());
        assertEquals(-122.4194, map.getCurrentLongitude());
        assertEquals(10, map.getZoomLevel());
        assertEquals(800, map.getWidth());
        assertEquals(600, map.getHeight());
    }

    @Test
    public void testSetCurrentLatitude() {
        Map map = new Map(37.7749, -122.4194, 10, 800, 600);
        map.setCurrentLatitude(40.7128);

        assertEquals(40.7128, map.getCurrentLatitude());
    }

    @Test
    public void testSetCurrentLongitude() {
        Map map = new Map(37.7749, -122.4194, 10, 800, 600);
        map.setCurrentLongitude(-74.0060);

        assertEquals(-74.0060, map.getCurrentLongitude());
    }

    @Test
    public void testSetZoomLevel() {
        Map map = new Map(37.7749, -122.4194, 10, 800, 600);
        map.setZoomLevel(12);

        assertEquals(12, map.getZoomLevel());
    }

    @Test
    public void testToString() {
        Map map = new Map(37.7749, -122.4194, 10, 800, 600);

        String expectedString = "Map{currentLatitude=37.7749, currentLongitude=-122.4194, zoomLevel=10, width=800, height=600}";
        assertEquals(expectedString, map.toString());
    }
}
