package utils;

import java.awt.*;

/**
 * Utility class for converting geographical locations (latitude and longitude) to map coordinates (pixels).
 * This class provides a method to convert latitude and longitude to a point on a map.
 */
public class LocationToMapCoordinate {

    /**
     * Converts geographical coordinates (latitude and longitude) to a point on a map.
     *
     * @param latitude the latitude to be converted
     * @param longitude the longitude to be converted
     * @param centerLat the latitude of the map's center
     * @param centerLng the longitude of the map's center
     * @param zoomLevel the zoom level of the map
     * @param mapWidth the width of the map in pixels
     * @param mapHeight the height of the map in pixels
     * @return the point on the map corresponding to the given latitude and longitude
     */
    public static Point convert(double latitude, double longitude, double centerLat, double centerLng, int zoomLevel, int mapWidth, int mapHeight) {
        double metersPerPixel = ZoomLevelToMeterPerPixel.zoomLevelToMeter(zoomLevel, centerLat);

        double deltaLat = latitude - centerLat;
        double deltaLng = longitude - centerLng;

        // Convert latitude and longitude differences into meters
        int y = (int) ((deltaLat * 111320) / metersPerPixel);
        int x = (int) ((deltaLng * 111320 * Math.cos(Math.toRadians(centerLat))) / metersPerPixel);

        // Calculate the pixel coordinates
        int pixelX = mapWidth / 2 + x;
        int pixelY = mapHeight / 2 - y;

        return new Point(pixelX, pixelY);
    }
}
