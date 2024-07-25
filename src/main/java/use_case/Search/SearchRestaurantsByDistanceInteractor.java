package use_case.Search;

import entity.Restaurant;
import use_case.UI.SearchInput;
import domain.SearchInputBoundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchRestaurantsByDistanceInteractor implements SearchRestaurantsByDistance {

    private final SearchInputBoundary searchInputBoundary;

    public SearchRestaurantsByDistanceInteractor(SearchInputBoundary searchInputBoundary) {
        this.searchInputBoundary = searchInputBoundary;
    }

    @Override
    public Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults) {
        try {


            // Perform the search
            Optional<List<Restaurant>> result = searchInputBoundary.execute(searchInput, maxResults);
            List<Restaurant> restaurants = result.orElse(new ArrayList<>());

            System.out.println("Search completed. Found " + restaurants.size() + " restaurant(s) within a radius of "
                    + searchInput.getDistance() + " meters from location ("
                    + searchInput.getLatitude() + ", " + searchInput.getLongitude() + ").");

            return Optional.of(restaurants);
        } catch (Exception e) {
            System.err.println("Error during restaurant search: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
