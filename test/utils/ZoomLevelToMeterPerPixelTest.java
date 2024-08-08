package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZoomLevelToMeterPerPixelTest {

    @Test
    public void testZoomLevelToMeter() {
        int zoomLevel = 15;
        double latitude = 43.6699136;

        // Calculate the expected value using the same formula
        double initialResolution = 156543.03392;
        double expectedMetersPerPixel = initialResolution * Math.cos(Math.toRadians(latitude)) / Math.pow(2, zoomLevel);

        double resultMetersPerPixel = ZoomLevelToMeterPerPixel.zoomLevelToMeter(zoomLevel, latitude);

        assertEquals(expectedMetersPerPixel, resultMetersPerPixel, 0.000001);
    }
}
