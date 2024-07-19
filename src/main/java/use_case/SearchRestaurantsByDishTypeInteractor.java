package use_case;

import entity.Location;
import entity.Restaurant;
import interface_adapter.PlacesAPIGateway;
import java.util.ArrayList;
import java.util.List;

public class SearchRestaurantsByDishTypeInteractor implements SearchRestaurantsByDishType {

    private final PlacesAPIGateway placesAPIGateway;

    public SearchRestaurantsByDishTypeInteractor(PlacesAPIGateway placesAPIGateway) {
        this.placesAPIGateway = placesAPIGateway;
    }

    @Override
    public List<Restaurant> search(Location location, String dishType, int radius, int maxResults) {
        try {
            return placesAPIGateway.getNearbyRestaurantsByDishType(location, dishType, radius, maxResults);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
