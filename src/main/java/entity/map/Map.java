package entity.map;

/**
 * Represents a map entity used to manage the display of maps with given parameters.
 */
public class Map {
    private double currentLatitude;
    private double currentLongitude;
    private int zoomLevel;
    private int width;
    private int height;

    /**
     * Constructs a Map object with the specified location, zoom level, and dimensions.
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

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public int getWidth() {
        return width;
    }

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
