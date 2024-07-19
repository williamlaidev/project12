package use_case;

import entity.Location;
import entity.Restaurant;
import java.util.List;

public interface SearchRestaurantsByDistance {
    List<Restaurant> search(Location location, int radius, int maxResults);
}
