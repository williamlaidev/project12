package main.java.entity;
import java.util.List;

/**
 * Factory class for creating Map instances.
 */
public class MapFactory {

    /**
     * @param currentLatitude         the latitude of the current map center
     * @param currentLongitude        the longitude of the current map center
     * @param zoomLevel               the zoom level of the map
     * @param displayedRestaurantIds  the list of restaurant IDs to display on the map
     * @return a new Map object
     */
    public static Map createMap(double currentLatitude, double currentLongitude, int zoomLevel, List<Integer> displayedRestaurantIds) {
        return new Map(currentLatitude, currentLongitude, zoomLevel, displayedRestaurantIds);
    }
}
