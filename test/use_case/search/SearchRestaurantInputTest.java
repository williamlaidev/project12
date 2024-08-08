package use_case.search;

import entity.DishType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchRestaurantInputTest {

    @Test
    public void testGetLatitude() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        String distance = "500";
        DishType dishType = DishType.AMERICAN;

        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        assertEquals(latitude, input.getLatitude(), "Latitude should match the provided value.");
    }

    @Test
    public void testGetLongitude() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        String distance = "500";
        DishType dishType = DishType.AMERICAN;

        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        assertEquals(longitude, input.getLongitude(), "Longitude should match the provided value.");
    }

    @Test
    public void testGetDistance() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        String distance = "500";
        DishType dishType = DishType.AMERICAN;

        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        assertEquals(distance, input.getDistance(), "Distance should match the provided value.");
    }

    @Test
    public void testGetDishType() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        String distance = "500";
        DishType dishType = DishType.AMERICAN;

        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        assertEquals(dishType, input.getDishType(), "DishType should match the provided value.");
    }

    @Test
    public void testToString() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        String distance = "500";
        DishType dishType = DishType.AMERICAN;

        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, distance, dishType);

        String expectedString = "SearchRestaurantInput{latitude=40.7128, longitude=-74.006, distance='500', dishType=AMERICAN}";
        assertEquals(expectedString, input.toString(), "toString should return the expected string representation.");
    }
}
