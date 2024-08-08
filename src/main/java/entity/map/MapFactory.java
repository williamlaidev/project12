package entity.map;

import java.util.List;

public interface MapFactory {
    Map createMap(double currentLatitude, double currentLongitude, int zoomLevel, List<Integer> displayedRestaurantIds);
}
