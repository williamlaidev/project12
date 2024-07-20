package use_case;

import entity.Location;
import entity.Restaurant;
import interface_adapter.IPlacesAPIGateway;
import java.util.ArrayList;
import java.util.List;

public class SearchRestaurantsByDishTypeInteractor implements SearchRestaurantsByDishType {

    private final IPlacesAPIGateway placesAPIGateway;

    public SearchRestaurantsByDishTypeInteractor(IPlacesAPIGateway placesAPIGateway) {
        this.placesAPIGateway = placesAPIGateway;
    }

    @Override
    public List<Restaurant> search(Location location, String dishType, int radius, int maxResults) {
        List<Restaurant> results = new ArrayList<>();
        try {
            results = placesAPIGateway.getNearbyRestaurantsByDishType(location, dishType, radius, maxResults);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
}
