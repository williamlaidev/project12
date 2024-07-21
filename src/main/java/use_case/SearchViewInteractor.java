package use_case;

import entity.Location;
import interface_adapter.PlacesAPIGateway;

public class SearchViewInteractor implements SearchInputBoundary {
    @Override
    public void execute(SearchInputData searchInputData) {
        double latitude = searchInputData.getLatitude();
        double longitude = searchInputData.getLongitude();
        String distance = searchInputData.getDistance();
        String dishType = searchInputData.getDishType();

        Location location = new Location(latitude, longitude);
        int radius = (int) Math.round(Double.parseDouble(distance));

        // Print the received information
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Distance: " + distance);
        System.out.println("Radius: " + radius);
        System.out.println("Dish Type: " + dishType);


        if (dishType == "ALL") {
            SearchRestaurantsByDistanceInteractor restaurants = new SearchRestaurantsByDistanceInteractor(new PlacesAPIGateway());
            System.out.println(restaurants.search(location, radius, 10));
        }
        else {
            SearchRestaurantsByDishTypeInteractor restaurants = new SearchRestaurantsByDishTypeInteractor(new PlacesAPIGateway());
            System.out.println(restaurants.search(location, dishType, radius, 10));
        }

    }
}
