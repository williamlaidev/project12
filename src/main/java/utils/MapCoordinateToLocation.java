package utils;

public class MapCoordinateToLocation {

    /**
     * Converts a map coordinate (x, y) to a geographical location (latitude, longitude).
     *
     * @param zoomLevel   The zoom level.
     * @param centerLat   The latitude at the center of the map.
     * @param centerLon   The longitude at the center of the map.
     * @param mapWidth    The width of the map in pixels.
     * @param mapHeight   The height of the map in pixels.
     * @param x           The x coordinate on the map.
     * @param y           The y coordinate on the map.
     * @return A double array containing the new latitude and longitude.
     */
    public static double[] mapCoordinateToLocation(int zoomLevel, double centerLat, double centerLon, int mapWidth, int mapHeight, int x, int y) {
        // Calculate meters per pixel
        double metersPerPixel = ZoomLevelToMeterPerPixel.zoomLevelToMeter(zoomLevel, centerLat);

        // Calculate distances from the center in pixels
        int[] center = MapCenterCalculator.calculateMapCenter(mapWidth, mapHeight);
        int deltaX = x - center[0];
        int deltaY = y - center[1];

        // Convert pixel distances to meters
        double distanceX = deltaX * metersPerPixel;
        double distanceY = deltaY * metersPerPixel;

        // Calculate new latitude and longitude
        double newLat = centerLat + (distanceY / 6378137.0) * (180 / Math.PI);
        double newLon = centerLon + (distanceX / 6378137.0) * (180 / Math.PI) / Math.cos(Math.toRadians(centerLat));

        return new double[]{newLat, newLon};
    }

    public static void main(String[] args) {
        // Example usage
        int zoomLevel = 15;
        double centerLat = 43.6699136;
        double centerLon = -79.4034176;
        int mapWidth = 200;
        int mapHeight = 200;
        int x = 150;
        int y = 150;

        double[] newLocation = mapCoordinateToLocation(zoomLevel, centerLat, centerLon, mapWidth, mapHeight, x, y);
        System.out.println("New location for coordinates (" + x + ", " + y + ") is: Latitude = " + newLocation[0] + ", Longitude = " + newLocation[1]);
    }
}
