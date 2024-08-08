package utils;

import java.awt.*;

/**
 * Utility for converting geographical coordinates to map pixel coordinates.
 */
public class LocationToMapCoordinate {

    /**
     * Converts latitude and longitude to map coordinates.
     *
     * @param latitude the latitude to convert
     * @param longitude the longitude to convert
     * @param centerLat the latitude of the map center
     * @param centerLng the longitude of the map center
     * @param zoomLevel the zoom level of the map
     * @param mapWidth the width of the map in pixels
     * @param mapHeight the height of the map in pixels
     * @return the map coordinates corresponding to the given latitude and longitude
     */
    public static Point convert(double latitude, double longitude, double centerLat, double centerLng, int zoomLevel, int mapWidth, int mapHeight) {
        double metersPerPixel = ZoomLevelToMeterPerPixel.zoomLevelToMeter(zoomLevel, centerLat);

        double deltaLat = latitude - centerLat;
        double deltaLng = longitude - centerLng;

        // Convert latitude and longitude differences to meters
        int y = (int) ((deltaLat * 111320) / metersPerPixel);
        int x = (int) ((deltaLng * 111320 * Math.cos(Math.toRadians(centerLat))) / metersPerPixel);

        // Calculate pixel coordinates
        int pixelX = mapWidth / 2 + x;
        int pixelY = mapHeight / 2 - y;

        return new Point(pixelX, pixelY);
    }
}
