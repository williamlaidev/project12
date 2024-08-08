package utils;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapCoordinateToLocationTest {

    @Test
    public void testConvert() {
        Point point = new Point(100, 100);
        double centerLat = 43.6699136;
        double centerLng = -79.393333;
        int zoomLevel = 15;
        int mapWidth = 200;
        int mapHeight = 200;

        double[] expectedLocation = {43.6699136, -79.393333};
        double[] resultLocation = MapCoordinateToLocation.convert(point, centerLat, centerLng, zoomLevel, mapWidth, mapHeight);

        assertArrayEquals(expectedLocation, resultLocation, 0.000001);
    }
}
