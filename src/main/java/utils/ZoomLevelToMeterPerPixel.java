package utils;

/**
 * Converts zoom levels to meters per pixel.
 */
public class ZoomLevelToMeterPerPixel {

    /**
     * Converts zoom level to meters per pixel at a specified latitude.
     *
     * @param zoomLevel the zoom level
     * @param latitude  the latitude
     * @return meters per pixel at the specified zoom level and latitude
     */
    public static double zoomLevelToMeter(int zoomLevel, double latitude) {
        final double initialResolution = 156543.03392; // Resolution for zoom level 0
        return initialResolution * Math.cos(Math.toRadians(latitude)) / Math.pow(2, zoomLevel);
    }

    public static void main(String[] args) {
        // Example usage
        int zoomLevel = 15;
        double latitude = 43.6699136;
        double metersPerPixel = zoomLevelToMeter(zoomLevel, latitude);
        System.out.println("Meters per pixel at zoom level " + zoomLevel + " and latitude " + latitude + " is: " + metersPerPixel);
    }
}
