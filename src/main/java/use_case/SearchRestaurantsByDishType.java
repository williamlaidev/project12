package use_case;

import entity.Location;
import entity.Restaurant;
import java.util.List;

public interface SearchRestaurantsByDishType {
    List<Restaurant> search(Location location, String dishType, int radius, int maxResults);
}

