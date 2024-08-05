package interface_adapter.search;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import framework.search.GooglePlacesRestaurantSearchService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantMapperTest {

    @Mock
    private GooglePlacesRestaurantSearchService placesService;

    private RestaurantMapper restaurantMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantMapper = new RestaurantMapper(placesService);
    }

    @Test
    public void testMapToRestaurantWithFilterMatch() {
        // Mocking the JSON response from Google Places API
        JSONObject placeJson = new JSONObject();
        placeJson.put("place_id", "test_place_id");
        placeJson.put("name", "Test Chinese Restaurant");
        placeJson.put("vicinity", "456 Test Avenue");
        placeJson.put("rating", 4.0);

        JSONObject locationJson = new JSONObject();
        locationJson.put("lat", 34.0522);
        locationJson.put("lng", -118.2437);

        JSONObject geometryJson = new JSONObject();
        geometryJson.put("location", locationJson);
        placeJson.put("geometry", geometryJson);

        JSONArray typesJson = new JSONArray();
        typesJson.put("chinese_restaurant");
        placeJson.put("types", typesJson);

        // No photos in this test case
        placeJson.put("photos", new JSONArray());

        // Mapping the JSON to Restaurant object
        Restaurant restaurant = restaurantMapper.mapToRestaurant(placeJson, DishType.CHINESE);

        assertNotNull(restaurant);
        assertEquals("test_place_id", restaurant.getRestaurantId());
        assertEquals("Test Chinese Restaurant", restaurant.getName());
        assertEquals(new Location(34.0522, -118.2437), restaurant.getLocation());
        assertEquals("456 Test Avenue", restaurant.getAddress());
        assertEquals(DishType.CHINESE, restaurant.getDishType());
        assertEquals(4.0, restaurant.getAverageRating());
        assertEquals("", restaurant.getPhotoUrl()); // No photo URL
    }

    @Test
    public void testMapToRestaurantWithFilterNoMatch() {
        // Mocking the JSON response from Google Places API
        JSONObject placeJson = new JSONObject();
        placeJson.put("place_id", "test_place_id");
        placeJson.put("name", "Test Mexican Restaurant");
        placeJson.put("vicinity", "789 Test Boulevard");
        placeJson.put("rating", 3.5);

        JSONObject locationJson = new JSONObject();
        locationJson.put("lat", 51.5074);
        locationJson.put("lng", -0.1278);

        JSONObject geometryJson = new JSONObject();
        geometryJson.put("location", locationJson);
        placeJson.put("geometry", geometryJson);

        JSONArray typesJson = new JSONArray();
        typesJson.put("mexican_restaurant");
        placeJson.put("types", typesJson);

        // No photos in this test case
        placeJson.put("photos", new JSONArray());

        // Mapping the JSON to Restaurant object
        Restaurant restaurant = restaurantMapper.mapToRestaurant(placeJson, DishType.CHINESE);

        assertNull(restaurant); // Should return null since the filter does not match
    }

    @Test
    public void testMapToRestaurantWithMultipleTypes() {
        // Mocking the JSON response from Google Places API
        JSONObject placeJson = new JSONObject();
        placeJson.put("place_id", "test_place_id");
        placeJson.put("name", "Test Fusion Restaurant");
        placeJson.put("vicinity", "101 Test Road");
        placeJson.put("rating", 4.3);

        JSONObject locationJson = new JSONObject();
        locationJson.put("lat", 48.8566);
        locationJson.put("lng", 2.3522);

        JSONObject geometryJson = new JSONObject();
        geometryJson.put("location", locationJson);
        placeJson.put("geometry", geometryJson);

        JSONArray typesJson = new JSONArray();
        typesJson.put("french_restaurant");
        typesJson.put("bakery");
        placeJson.put("types", typesJson);

        JSONArray photosJson = new JSONArray();
        JSONObject photoJson = new JSONObject();
        photoJson.put("photo_reference", "test_photo_reference");
        photosJson.put(photoJson);
        placeJson.put("photos", photosJson);

        when(placesService.buildPhotoUrl("test_photo_reference")).thenReturn("http://example.com/photo.jpg");

        // Mapping the JSON to Restaurant object
        Restaurant restaurant = restaurantMapper.mapToRestaurant(placeJson, DishType.FRENCH);

        assertNotNull(restaurant);
        assertEquals("test_place_id", restaurant.getRestaurantId());
        assertEquals("Test Fusion Restaurant", restaurant.getName());
        assertEquals(new Location(48.8566, 2.3522), restaurant.getLocation());
        assertEquals("101 Test Road", restaurant.getAddress());
        assertEquals(DishType.FRENCH, restaurant.getDishType());
        assertEquals(4.3, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }
}
