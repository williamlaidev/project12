package domain;

import use_case.search.RestaurantSearchInput;

public interface SearchInputBoundary {
    public void execute(RestaurantSearchInput restaurantSearchInput, int maxResults);
}
