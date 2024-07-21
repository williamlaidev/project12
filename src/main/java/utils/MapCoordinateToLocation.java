package utils;

import java.awt.*;

public class MapCoordinateToLocation {
    public static double[] convert(Point point, double centerLat, double centerLng, int zoomLevel, int mapWidth, int mapHeight) {
        double metersPerPixel = ZoomLevelToMeterPerPixel.zoomLevelToMeter(zoomLevel, centerLat);

        double deltaLat = (point.y - mapHeight / 2.0) * metersPerPixel / 111320; // Convert meters to latitude
        double deltaLng = (point.x - mapWidth / 2.0) * metersPerPixel / (111320 * Math.cos(Math.toRadians(centerLat))); // Convert meters to longitude

        double newLat = centerLat - deltaLat;
        double newLng = centerLng + deltaLng;

        return new double[]{newLat, newLng};
    }
}
