package domain;

import entity.Restaurant;
import use_case.search.RestaurantSearchInput;

import java.util.List;
import java.util.Optional;

public interface SearchRestaurantService {

    Optional<List<Restaurant>> execute(RestaurantSearchInput restaurantSearchInput, int maxResults) throws Exception;
}
