package use_case.search;

import entity.restaurant.Restaurant;

import java.util.List;

public interface SearchRestaurants {
    List<Restaurant> execute(SearchRestaurantInput restaurantSearchInput, int maxRestaurantsToSearch, int maximumResults);
}