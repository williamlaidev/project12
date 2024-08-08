package use_case.search;

import entity.DishType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchRestaurantInputTest {

    @Test
    public void testSearchRestaurantInput() {
        double latitude = 37.7749;
        double longitude = -122.4194;
        String distance = "1000";
        DishType dishType = DishType.ITALIAN;

        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        assertEquals(latitude, input.getLatitude());
        assertEquals(longitude, input.getLongitude());
        assertEquals(distance, input.getDistance());
        assertEquals(dishType, input.getDishType());
    }

    @Test
    public void testToString() {
        double latitude = 37.7749;
        double longitude = -122.4194;
        String distance = "1000";
        DishType dishType = DishType.ITALIAN;

        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, distance, dishType);
        String expectedString = "SearchInput{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance='" + distance + '\'' +
                ", dishType=" + dishType +
                '}';

        assertEquals(expectedString, input.toString());
    }
}
