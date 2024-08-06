package interface_adapter.search;

import entity.DishType;
import entity.location.Location;
import entity.restaurant.Restaurant;
import entity.restaurant.RestaurantFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GooglePlacesRestaurantSearchAdapterTest {

    private RestaurantFactory restaurantFactory;
    private GooglePlacesRestaurantSearchAdapter adapter;

    @BeforeEach
    public void setUp() {
        restaurantFactory = mock(RestaurantFactory.class);
        adapter = new GooglePlacesRestaurantSearchAdapter(restaurantFactory);
    }

    @Test
    public void testAdaptToRestaurantWithAllFields() {
        JSONObject placeJson = new JSONObject()
                .put("place_id", "ChIJN1t_tDeuEmsRUsoyG83frY4")
                .put("name", "Test Restaurant")
                .put("types", new JSONArray().put("italian_restaurant"))
                .put("geometry", new JSONObject()
                        .put("location", new JSONObject()
                                .put("lat", 40.7128)
                                .put("lng", -74.0060)))
                .put("vicinity", "123 Main Street")
                .put("rating", 4.5);

        Location expectedLocation = new Location(40.7128, -74.0060);
        Restaurant expectedRestaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", expectedLocation, "123 Main Street", DishType.ITALIAN, 4.5, "");

        when(restaurantFactory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", expectedLocation, "123 Main Street", DishType.ITALIAN, 4.5, "")).thenReturn(expectedRestaurant);

        Restaurant result = adapter.adaptToRestaurant(placeJson);

        assertNotNull(result);
        assertEquals(expectedRestaurant, result);
    }

    @Test
    public void testAdaptToRestaurantWithEmptyTypes() {
        JSONObject placeJson = new JSONObject()
                .put("place_id", "ChIJN1t_tDeuEmsRUsoyG83frY4")
                .put("name", "Test Restaurant")
                .put("types", new JSONArray())
                .put("geometry", new JSONObject()
                        .put("location", new JSONObject()
                                .put("lat", 40.7128)
                                .put("lng", -74.0060)))
                .put("vicinity", "123 Main Street")
                .put("rating", 4.5);

        Location expectedLocation = new Location(40.7128, -74.0060);
        Restaurant expectedRestaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", expectedLocation, "123 Main Street", null, 4.5, "");

        when(restaurantFactory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", expectedLocation, "123 Main Street", null, 4.5, "")).thenReturn(expectedRestaurant);

        Restaurant result = adapter.adaptToRestaurant(placeJson);

        assertNotNull(result);
        assertEquals(expectedRestaurant, result);
    }

    @Test
    public void testAdaptToRestaurantWithNullTypes() {
        JSONObject placeJson = new JSONObject()
                .put("place_id", "ChIJN1t_tDeuEmsRUsoyG83frY4")
                .put("name", "Test Restaurant")
                .put("geometry", new JSONObject()
                        .put("location", new JSONObject()
                                .put("lat", 40.7128)
                                .put("lng", -74.0060)))
                .put("vicinity", "123 Main Street")
                .put("rating", 4.5);

        Location expectedLocation = new Location(40.7128, -74.0060);
        Restaurant expectedRestaurant = new Restaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", expectedLocation, "123 Main Street", null, 4.5, "");

        when(restaurantFactory.createRestaurant("ChIJN1t_tDeuEmsRUsoyG83frY4", "Test Restaurant", expectedLocation, "123 Main Street", null, 4.5, "")).thenReturn(expectedRestaurant);

        Restaurant result = adapter.adaptToRestaurant(placeJson);

        assertNotNull(result);
        assertEquals(expectedRestaurant, result);
    }
}
