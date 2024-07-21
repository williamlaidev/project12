package use_case;

import entity.Location;
import interface_adapter.PlacesAPIGateway;
import interface_adapter.SearchViewPresenter;

public class SearchViewInteractor implements SearchInputBoundary {
    private final SearchViewPresenter searchViewPresenter;

    public SearchViewInteractor(SearchViewPresenter searchViewPresenter) {
        this.searchViewPresenter = searchViewPresenter;
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
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid distance input. Please enter a number.");
        }
    }
}








