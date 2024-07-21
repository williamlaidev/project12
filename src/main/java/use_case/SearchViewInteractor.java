package use_case;

import entity.Location;
import interface_adapter.PlacesAPIGateway;

public class SearchViewInteractor implements SearchInputBoundary {
    private final SearchOutputBoundary searchOutputBoundary;

    public SearchViewInteractor(SearchOutputBoundary searchOutputBoundary) {
        this.searchOutputBoundary = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        double latitude = searchInputData.getLatitude();
        double longitude = searchInputData.getLongitude();
        String distance = searchInputData.getDistance();
        String dishType = searchInputData.getDishType();

        try {
            double distanceValue = Double.parseDouble(distance);
            int roundedDistance = (int) Math.round(distanceValue);
            Location location = new Location(latitude, longitude);

            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
            System.out.println("Distance: " + roundedDistance);
            System.out.println("Dish Type: " + dishType);

            int radius = roundedDistance;

            if ("ALL".equalsIgnoreCase(dishType)) {
                SearchRestaurantsByDistanceInteractor restaurants = new SearchRestaurantsByDistanceInteractor(new PlacesAPIGateway());
                System.out.println(restaurants.search(location, radius, 10));
            } else {
                SearchRestaurantsByDishTypeInteractor restaurants = new SearchRestaurantsByDishTypeInteractor(new PlacesAPIGateway());
                System.out.println(restaurants.search(location, dishType, radius, 10));
            }
        } catch (NumberFormatException e) {
            searchOutputBoundary.prepareFailView("Invalid distance input. Please enter a number.");
        }
    }
}








