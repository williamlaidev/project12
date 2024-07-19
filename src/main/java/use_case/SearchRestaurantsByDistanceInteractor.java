package use_case;

import entity.Location;
import entity.Restaurant;
import interface_adapter.PlacesAPIGateway;
import java.util.ArrayList;
import java.util.List;

public class SearchRestaurantsByDistanceInteractor implements SearchRestaurantsByDistance {

    private final PlacesAPIGateway placesAPIGateway;

    public SearchRestaurantsByDistanceInteractor(PlacesAPIGateway placesAPIGateway) {
        this.placesAPIGateway = placesAPIGateway;
    }

    @Override
    public List<Restaurant> search(Location location, int radius, int maxResults) {
        try {
            return placesAPIGateway.getNearbyRestaurants(location, radius, maxResults);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
