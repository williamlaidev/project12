package utils;

public class ZoomLevelToMeter {

    /**
     * Calculates the map size in meters for the given zoom level and latitude.
     *
     * @param zoomLevel The zoom level of the map.
     * @param latitude  The latitude of the map center.
     * @param pixels    The size of the map in pixels (assuming a square map).
     * @return The map size in meters.
     */
    public static double zoomLevelToMeter(int zoomLevel, double latitude, int pixels) {
        final double initialResolution = 156543.03392; // Initial resolution (meters/pixel) for zoom level 0
        double resolution = (initialResolution * Math.cos(Math.toRadians(latitude))) / Math.pow(2, zoomLevel);
        return resolution * pixels;
    }


    public static void main(String[] args) {
        // Example usage
        int zoomLevel = 15;
        double latitude = 43.6699136;
        int pixels = 200; // 200x200 pixel map

        double mapSizeMeters = zoomLevelToMeter(zoomLevel, latitude, pixels);
        System.out.println("Map size for a " + pixels + "x" + pixels + " pixel map at zoom level " + zoomLevel +
                " and latitude " + latitude + " is: " + mapSizeMeters + " meters");
    }
}
