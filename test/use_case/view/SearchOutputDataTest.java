package use_case.view;

import entity.DishType;
import entity.location.Location;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchOutputDataTest {

    @Test
    void testGetRestaurantsInfo() {
        Location location = new Location(40.7128, -74.0060);
        Restaurant restaurant1 = new Restaurant("1", "Restaurant A", location, "123 Main St", DishType.AMERICAN, 4.5, "http://example.com/photo1.jpg");
        Restaurant restaurant2 = new Restaurant("2", "Restaurant B", location, "456 Elm St", DishType.ITALIAN, 3.5, "http://example.com/photo2.jpg");

        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);
        SearchOutputData outputData = new SearchOutputData(restaurants);

        List<String> expectedInfo = Arrays.asList(
                "1 - Restaurant A - 123 Main St - Location{latitude=40.7128, longitude=-74.006} - AMERICAN - Rating: 4.50 - http://example.com/photo1.jpg",
                "2 - Restaurant B - 456 Elm St - Location{latitude=40.7128, longitude=-74.006} - ITALIAN - Rating: 3.50 - http://example.com/photo2.jpg"
        );

        assertEquals(expectedInfo, outputData.getRestaurantsInfo());
    }
}
