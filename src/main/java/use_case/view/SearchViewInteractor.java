package use_case.view;

import domain.SearchRestaurantService;
import entity.DishType;
import entity.Restaurant;
import use_case.search.RestaurantSearchInput;
import use_case.search.SearchRestaurantsByDistanceInteractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchViewInteractor implements SearchRestaurantService {

    private final SearchRestaurantsByDistanceInteractor restaurantsInteractor;

    public SearchViewInteractor(SearchRestaurantsByDistanceInteractor restaurantsInteractor) {
        this.restaurantsInteractor = restaurantsInteractor;
    }

    @Override
    public Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maxResults) throws Exception {
        double latitude = restaurantSearchInput.getLatitude();
        double longitude = restaurantSearchInput.getLongitude();
        String distance = restaurantSearchInput.getDistance();
        DishType dishType = restaurantSearchInput.getDishType();

        try {
            double distanceValue = Double.parseDouble(distance);
            String roundedDistance = Integer.toString((int) Math.round(distanceValue));

            // Create RestaurantSearchInput instance to pass to the interactor
            RestaurantSearchInput searchInputByDistance = new RestaurantSearchInput(latitude, longitude, roundedDistance, dishType);

            // Use SearchRestaurantsByDistanceInteractor for the search
            List<Restaurant> results = restaurantsInteractor.execute(searchInputByDistance, maxResults).orElse(new ArrayList<>());

            return Optional.of(results);
        } catch (NumberFormatException numberFormatException) {
            // FIXME: Provide a more descriptive message specifying the required input type (positive integer or non-negative number)
            System.out.println("Invalid distance input. Please enter a number.");
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("An error occurred during the search: " + e.getMessage());
            return Optional.empty();
        }
    }
}
