package entity.map;

/**
 * Represents a map with a specific center location, zoom level, and dimensions.
 */
public class Map {
    private double currentLatitude;
    private double currentLongitude;
    private int zoomLevel;
    private final int width;
    private final int height;

    /**
     * Constructs a Map object with the specified parameters.
     *
     * @param currentLatitude  the latitude of the map's center
     * @param currentLongitude the longitude of the map's center
     * @param zoomLevel        the zoom level of the map
     * @param width            the width of the map in pixels
     * @param height           the height of the map in pixels
     */
    public Map(double currentLatitude, double currentLongitude, int zoomLevel, int width, int height) {
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.zoomLevel = zoomLevel;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the current latitude of the map's center.
     *
     * @return the latitude of the map's center
     */
    public double getCurrentLatitude() {
        return currentLatitude;
    }

    /**
     * Sets the current latitude of the map's center.
     *
     * @param currentLatitude the new latitude of the map's center
     */
    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    /**
     * Returns the current longitude of the map's center.
     *
     * @return the longitude of the map's center
     */
    public double getCurrentLongitude() {
        return currentLongitude;
    }

    /**
     * Sets the current longitude of the map's center.
     *
     * @param currentLongitude the new longitude of the map's center
     */
    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    /**
     * Returns the current zoom level of the map.
     *
     * @return the zoom level of the map
     */
    public int getZoomLevel() {
        return zoomLevel;
    }

    /**
     * Sets the zoom level of the map.
     *
     * @param zoomLevel the new zoom level of the map
     */
    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    /**
     * Returns the width of the map in pixels.
     *
     * @return the width of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the map in pixels.
     *
     * @return the height of the map
     */
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Map{" +
                "currentLatitude=" + currentLatitude +
                ", currentLongitude=" + currentLongitude +
                ", zoomLevel=" + zoomLevel +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
