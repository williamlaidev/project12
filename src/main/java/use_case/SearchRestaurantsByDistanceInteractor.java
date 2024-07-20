package use_case;

import entity.Location;
import entity.Restaurant;
import interface_adapter.IPlacesAPIGateway;
import java.util.ArrayList;
import java.util.List;

public class SearchRestaurantsByDistanceInteractor implements SearchRestaurantsByDistance {

    private final IPlacesAPIGateway placesAPIGateway;

    public SearchRestaurantsByDistanceInteractor(IPlacesAPIGateway placesAPIGateway) {
        this.placesAPIGateway = placesAPIGateway;
    }

    @Override
    public List<Restaurant> search(Location location, int radius, int maxResults) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            restaurants = placesAPIGateway.getNearbyRestaurants(location, radius, maxResults);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return restaurants;
    }
}