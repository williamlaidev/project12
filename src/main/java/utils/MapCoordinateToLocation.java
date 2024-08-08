package utils;

import java.awt.*;

/**
 * Converts map coordinates to geographical latitude and longitude.
 */
public class MapCoordinateToLocation {

    /**
     * Converts a map point to geographical coordinates.
     *
     * @param point       the point on the map to convert
     * @param centerLat   the latitude of the map's center
     * @param centerLng   the longitude of the map's center
     * @param zoomLevel   the zoom level of the map
     * @param mapWidth    the width of the map in pixels
     * @param mapHeight   the height of the map in pixels
     * @return an array containing the latitude and longitude of the point
     */
    public static double[] convert(Point point, double centerLat, double centerLng, int zoomLevel, int mapWidth, int mapHeight) {
        double metersPerPixel = ZoomLevelToMeterPerPixel.zoomLevelToMeter(zoomLevel, centerLat);

        double deltaLat = (point.y - mapHeight / 2.0) * metersPerPixel / 111320;
        double deltaLng = (point.x - mapWidth / 2.0) * metersPerPixel / (111320 * Math.cos(Math.toRadians(centerLat)));

        double newLat = centerLat - deltaLat;
        double newLng = centerLng + deltaLng;

        return new double[]{newLat, newLng};
    }
}
