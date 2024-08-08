package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZoomLevelToMeterTest {

    @Test
    public void testZoomLevelToMeter() {
        int zoomLevel = 15;
        double latitude = 43.6699136;
        int pixels = 200;

        double expectedMapSizeMeters = 1785.012724; // Example expected value
        double resultMapSizeMeters = ZoomLevelToMeter.zoomLevelToMeter(zoomLevel, latitude, pixels);

        assertEquals(expectedMapSizeMeters, resultMapSizeMeters, 0.001);
    }
}
