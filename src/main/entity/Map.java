package main.entity;
import java.util.List;

/**
 * Represents a map integration for displaying user and restaurant locations.
 */
public class Map {
    private double currentLatitude;
    private double currentLongitude;
    private int zoomLevel;
    private List<Integer> displayedRestaurantIds; // List of restaurant IDs to display on the map

    /**
     * @param currentLatitude         the latitude of the current map center
     * @param currentLongitude        the longitude of the current map center
     * @param zoomLevel               the zoom level of the map
     * @param displayedRestaurantIds  the list of restaurant IDs to display on the map
     */
    public Map(double currentLatitude, double currentLongitude, int zoomLevel, List<Integer> displayedRestaurantIds) {
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.zoomLevel = zoomLevel;
        this.displayedRestaurantIds = displayedRestaurantIds;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public List<Integer> getDisplayedRestaurantIds() {
        return displayedRestaurantIds;
    }

    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public void setDisplayedRestaurantIds(List<Integer> displayedRestaurantIds) {
        this.displayedRestaurantIds = displayedRestaurantIds;
    }

    @Override
    public String toString() {
        return "Map{" +
                "currentLatitude=" + currentLatitude +
                ", currentLongitude=" + currentLongitude +
                ", zoomLevel=" + zoomLevel +
                ", displayedRestaurantIds=" + displayedRestaurantIds +
                '}';
    }
}
