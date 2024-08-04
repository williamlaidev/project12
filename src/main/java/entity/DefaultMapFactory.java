package entity;

import java.util.List;

public class DefaultMapFactory implements MapFactory {

    @Override
    public Map createMap(double currentLatitude, double currentLongitude, int zoomLevel, List<Integer> displayedRestaurantIds) {
        return new Map(currentLatitude, currentLongitude, zoomLevel, displayedRestaurantIds);
    }
}
