package use_case.view;

import domain.SearchRestaurantService;
import entity.DishType;
import entity.Restaurant;
import use_case.search.RestaurantSearchInput;
import use_case.search.SearchRestaurantsByDistanceInteractor;
import entity.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Interactor that handles the business logic for searching restaurants and adjusting map zoom levels.
 */
public class SearchViewInteractor implements SearchRestaurantService {

    private final SearchRestaurantsByDistanceInteractor restaurantsInteractor;
    private Map currentMap; // Holds the current map context

    public SearchViewInteractor(SearchRestaurantsByDistanceInteractor restaurantsInteractor, Map initialMap) {
        this.restaurantsInteractor = restaurantsInteractor;
        this.currentMap = initialMap;
    }

    @Override
    public Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maxResults)  {
        double latitude = restaurantSearchInput.getLatitude();
        double longitude = restaurantSearchInput.getLongitude();
        String distance = restaurantSearchInput.getDistance();
        DishType dishType = restaurantSearchInput.getDishType();

        try {
            double distanceValue = Double.parseDouble(distance);
            String roundedDistance = Integer.toString((int) Math.round(distanceValue));

            RestaurantSearchInput searchInputByDistance = new RestaurantSearchInput(latitude, longitude, roundedDistance, dishType);
            List<Restaurant> results = restaurantsInteractor.execute(searchInputByDistance, maxResults).orElse(new ArrayList<>());
            return Optional.of(results);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid distance input. Please enter a number.");
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("An error occurred during the search: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Adjusts the zoom level of the map.
     *
     * @param zoomChange the change in zoom level (+1 or -1 typically)
     */
    public void adjustZoomLevel(int zoomChange) {
        int newZoomLevel = this.currentMap.getZoomLevel() + zoomChange;
        this.currentMap.setZoomLevel(newZoomLevel);
        // Optionally trigger a map refresh or update other components dependent on the zoom level
        System.out.println("Zoom level adjusted to: " + newZoomLevel);
        // Here you might also trigger a map refresh or update UI components
    }
}


