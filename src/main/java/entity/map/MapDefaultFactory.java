package entity.map;

/**
 * Default implementation of the {@link MapFactory} interface.
 * This factory creates a {@link Map} with the provided parameters.
 */
public class MapDefaultFactory implements MapFactory {

    /**
     * Creates a new {@link Map} instance using the specified parameters.
     *
     * @param currentLatitude the latitude of the map's center
     * @param currentLongitude the longitude of the map's center
     * @param zoomLevel the zoom level of the map
     * @param width the width of the map in pixels
     * @param height the height of the map in pixels
     * @return a new {@link Map} instance configured with the given parameters
     */
    @Override
    public Map createMap(double currentLatitude, double currentLongitude, int zoomLevel, int width, int height) {
        return new Map(currentLatitude, currentLongitude, zoomLevel, width, height);
    }
}
