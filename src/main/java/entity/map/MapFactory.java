package entity.map;

/**
 * Factory interface for creating instances of {@link Map}.
 */
public interface MapFactory {

    /**
     * Creates a new {@link Map} instance with the specified parameters.
     *
     * @param currentLatitude the latitude of the map's center
     * @param currentLongitude the longitude of the map's center
     * @param zoomLevel the zoom level of the map
     * @param width the width of the map in pixels
     * @param height the height of the map in pixels
     * @return a new {@link Map} instance configured with the provided parameters
     */
    Map createMap(double currentLatitude, double currentLongitude, int zoomLevel, int width, int height);
}
