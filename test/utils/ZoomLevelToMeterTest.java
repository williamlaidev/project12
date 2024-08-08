package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZoomLevelToMeterTest {

    @Test
    public void testZoomLevelToMeter() {
        int zoomLevel = 15;
        double latitude = 43.6699136;
        int pixels = 200;

        double initialResolution = 156543.03392;
        double expectedResolution = (initialResolution * Math.cos(Math.toRadians(latitude))) / Math.pow(2, zoomLevel);
        double expectedMapSizeMeters = expectedResolution * pixels;

        double resultMapSizeMeters = ZoomLevelToMeter.zoomLevelToMeter(zoomLevel, latitude, pixels);

        assertEquals(expectedMapSizeMeters, resultMapSizeMeters, 0.001);
    }
}
