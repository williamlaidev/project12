package use_case;

import domain.SearchInputBoundary;
import entity.DishType;
import entity.Restaurant;
import entity.SearchInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchViewInteractor implements SearchInputBoundary {

    private final SearchRestaurantsByDistanceInteractor restaurantsInteractor;

    public SearchViewInteractor(SearchRestaurantsByDistanceInteractor restaurantsInteractor) {
        this.restaurantsInteractor = restaurantsInteractor;
    }

    @Override
    public Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults) throws Exception {
        double latitude = searchInput.getLatitude();
        double longitude = searchInput.getLongitude();
        String distance = searchInput.getDistance();
        DishType dishType = searchInput.getDishType();

        try {
            double distanceValue = Double.parseDouble(distance);
            String roundedDistance = Integer.toString((int) Math.round(distanceValue));

            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
            System.out.println("Distance: " + roundedDistance);
            System.out.println("Dish Type: " + (dishType != null ? dishType : "ALL"));

            // Create SearchInput instance to pass to the interactor
            SearchInput searchInputForDistance = new SearchInput(latitude, longitude, roundedDistance, dishType);

            // Use SearchRestaurantsByDistanceInteractor for the search
            List<Restaurant> results = restaurantsInteractor.execute(searchInputForDistance, maxResults).orElse(new ArrayList<>());

            return Optional.of(results);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid distance input. Please enter a number.");
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("An error occurred during the search: " + e.getMessage());
            return Optional.empty();
        }
    }
}
