package entity.map;

import java.util.List;

public class MapDefaultFactory implements MapFactory {

    @Override
    public Map createMap(double currentLatitude, double currentLongitude, int zoomLevel, List<Integer> displayedRestaurantIds) {
        return new Map(currentLatitude, currentLongitude, zoomLevel, displayedRestaurantIds);
    }
}
