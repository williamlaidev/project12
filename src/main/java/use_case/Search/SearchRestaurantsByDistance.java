package use_case.Search;

import entity.Restaurant;
import use_case.UI.SearchInput;

import java.util.List;
import java.util.Optional;

public interface SearchRestaurantsByDistance {
    Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults);
}