package utils;

public class ZoomLevelToMeterPerPixel {

    /**
     * Converts the given zoom level to meters per pixel at the specified latitude.
     *
     * @param zoomLevel The zoom level.
     * @param latitude  The latitude.
     * @return The meters per pixel at the specified zoom level and latitude.
     */
    public static double zoomLevelToMeter(int zoomLevel, double latitude) {
        // Constants
        final double initialResolution = 156543.03392; // Initial resolution (meters/pixel) for zoom level 0

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
