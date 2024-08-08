package utils;

import java.awt.*;

/**
 * Utility class for converting map coordinates to geographical locations.
 * This class provides a method to convert a point on a map to latitude and longitude values.
 */

public class MapCoordinateToLocation {

    /**
     * Converts a point on a map to geographical coordinates (latitude and longitude).
     *
     * @param point the point on the map to be converted
     * @param centerLat the latitude of the map's center
     * @param centerLng the longitude of the map's center
     * @param zoomLevel the zoom level of the map
     * @param mapWidth the width of the map in pixels
     * @param mapHeight the height of the map in pixels
     * @return an array containing the latitude and longitude of the converted point
     */
    public static double[] convert(Point point, double centerLat, double centerLng, int zoomLevel, int mapWidth, int mapHeight) {
        double metersPerPixel = ZoomLevelToMeterPerPixel.zoomLevelToMeter(zoomLevel, centerLat);

        double deltaLat = (point.y - mapHeight / 2.0) * metersPerPixel / 111320; // Convert meters to latitude
        double deltaLng = (point.x - mapWidth / 2.0) * metersPerPixel / (111320 * Math.cos(Math.toRadians(centerLat))); // Convert meters to longitude

        double newLat = centerLat - deltaLat;
        double newLng = centerLng + deltaLng;

        return new double[]{newLat, newLng};
    }
}
