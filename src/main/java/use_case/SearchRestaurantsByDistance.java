package use_case;

import entity.Restaurant;
import entity.SearchInput;

import java.util.List;
import java.util.Optional;

public interface SearchRestaurantsByDistance {
    Optional<List<Restaurant>> execute(SearchInput searchInput, int maxResults);
}