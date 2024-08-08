package entity;

/**
 * Factory class for creating Map instances with specific dimensions.
 */
public class MapFactory {

    /**
     * Creates a new Map object centered at the specified coordinates with a specific zoom level.
     *
     * @param currentLatitude  the latitude of the current map center
     * @param currentLongitude the longitude of the current map center
     * @param zoomLevel        the zoom level of the map
     * @param width            the width of the map in pixels
     * @param height           the height of the map in pixels
     * @return a new Map object
     */
    public static Map createMap(double currentLatitude, double currentLongitude, int zoomLevel, int width, int height) {
        return new Map(currentLatitude, currentLongitude, zoomLevel, width, height);
    }
}
