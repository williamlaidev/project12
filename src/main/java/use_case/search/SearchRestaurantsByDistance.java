package use_case.search;

import entity.Restaurant;
import use_case.view.SearchInput;

import java.util.List;
import java.util.Optional;

public interface SearchRestaurantsByDistance {
    Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults);
}