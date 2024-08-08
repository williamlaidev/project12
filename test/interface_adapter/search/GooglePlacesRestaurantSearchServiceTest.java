package interface_adapter.search;

import entity.DishType;
import entity.location.Location;
import entity.restaurant.Restaurant;
import interface_adapter.view.SearchPresenter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.data.create.AddRestaurant;
import use_case.data.read.FindRestaurantById;
import use_case.data.update.UpdateRestaurant;
import use_case.search.FetchRestaurantPhotoUrl;
import use_case.search.SearchRestaurantInput;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class GooglePlacesRestaurantSearchServiceTest {

    private RestaurantSearchGateways searchGateways;
    private GooglePlacesRestaurantSearchAdapter inputAdapter;
    private AddRestaurant addRestaurantUseCase;
    private UpdateRestaurant updateRestaurantUseCase;
    private FindRestaurantById findRestaurantByIdUseCase;
    private FetchRestaurantPhotoUrl fetchRestaurantPhotoUrlUseCase;
    private GooglePlacesRestaurantSearchService service;
    private SearchPresenter searchPresenter;

    @BeforeEach
    public void setUp() {
        searchGateways = mock(RestaurantSearchGateways.class);
        inputAdapter = mock(GooglePlacesRestaurantSearchAdapter.class);
        addRestaurantUseCase = mock(AddRestaurant.class);
        updateRestaurantUseCase = mock(UpdateRestaurant.class);
        findRestaurantByIdUseCase = mock(FindRestaurantById.class);
        fetchRestaurantPhotoUrlUseCase = mock(FetchRestaurantPhotoUrl.class);
        searchPresenter = mock(SearchPresenter.class);
        service = new GooglePlacesRestaurantSearchService(searchGateways, inputAdapter, addRestaurantUseCase, updateRestaurantUseCase, findRestaurantByIdUseCase, fetchRestaurantPhotoUrlUseCase, searchPresenter);
    }

    @Test
    public void testFetchNearbyRestaurantsWithValidResponse() throws IOException {
        JSONObject locationJson = new JSONObject().put("lat", 37.7749).put("lng", -122.4194);
        JSONObject geometryJson = new JSONObject().put("location", locationJson);
        JSONObject placeJson = new JSONObject().put("place_id", "place1").put("name", "Restaurant 1").put("geometry", geometryJson).put("vicinity", "123 Main Street").put("rating", 4.5)
                .put("types", new JSONArray().put("chinese_restaurant"));
        JSONArray resultsJson = new JSONArray().put(placeJson);
        JSONObject responseJson = new JSONObject().put("results", resultsJson);

        when(searchGateways.fetchNearbyRestaurants(anyDouble(), anyDouble(), anyInt())).thenReturn(responseJson);

        Location expectedLocation = new Location(37.7749, -122.4194);
        Restaurant expectedRestaurant = new Restaurant("place1", "Restaurant 1", expectedLocation, "123 Main Street", DishType.CHINESE, 4.5, "");
        when(inputAdapter.adaptToRestaurant(any(JSONObject.class))).thenReturn(expectedRestaurant);
        when(fetchRestaurantPhotoUrlUseCase.execute(any(JSONObject.class))).thenReturn("http://example.com/photo.jpg");
        when(findRestaurantByIdUseCase.execute(anyString())).thenReturn(Optional.empty());
        SearchRestaurantInput searchInput = new SearchRestaurantInput(37.7749, -122.4194, "500", DishType.CHINESE);
        List<Restaurant> restaurants = service.fetchNearbyRestaurants(searchInput, 10, 5);
        assertEquals(1, restaurants.size());
        Restaurant result = restaurants.get(0);
        assertEquals("place1", result.getRestaurantId());
        assertEquals("Restaurant 1", result.getName());
        assertEquals(expectedLocation, result.getLocation());
        assertEquals("123 Main Street", result.getAddress());
        assertEquals(4.5, result.getAverageRating());
        assertEquals("http://example.com/photo.jpg", result.getPhotoUrl());
        verify(addRestaurantUseCase, times(1)).execute(any(Restaurant.class));
        verify(updateRestaurantUseCase, never()).execute(any(Restaurant.class));
    }

    @Test
    public void testFetchNearbyRestaurantsWithNoResults() throws IOException {
        JSONObject responseJson = new JSONObject().put("results", new JSONArray());
        when(searchGateways.fetchNearbyRestaurants(anyDouble(), anyDouble(), anyInt())).thenReturn(responseJson);
        SearchRestaurantInput searchInput = new SearchRestaurantInput(37.7749, -122.4194, "500", null);
        List<Restaurant> restaurants = service.fetchNearbyRestaurants(searchInput, 10, 5);
        assertTrue(restaurants.isEmpty());
        verify(addRestaurantUseCase, never()).execute(any(Restaurant.class));
        verify(updateRestaurantUseCase, never()).execute(any(Restaurant.class));
    }

    @Test
    public void testFetchNearbyRestaurantsWithIOException() throws IOException {
        when(searchGateways.fetchNearbyRestaurants(anyDouble(), anyDouble(), anyInt())).thenThrow(new IOException("Failed to fetch data"));

        SearchRestaurantInput searchInput = new SearchRestaurantInput(37.7749, -122.4194, "500", null);

        List<Restaurant> restaurants = service.fetchNearbyRestaurants(searchInput, 10, 5);

        assertTrue(restaurants.isEmpty());

        verify(addRestaurantUseCase, never()).execute(any(Restaurant.class));
        verify(updateRestaurantUseCase, never()).execute(any(Restaurant.class));
    }

    @Test
    public void testFetchNearbyRestaurantsWithMatchingDishType() throws IOException {
        JSONObject locationJson = new JSONObject().put("lat", 37.7749).put("lng", -122.4194);
        JSONObject geometryJson = new JSONObject().put("location", locationJson);
        JSONObject placeJson = new JSONObject().put("place_id", "place1").put("name", "Restaurant 1").put("geometry", geometryJson).put("vicinity", "123 Main Street").put("rating", 4.5)
                .put("types", new JSONArray().put("chinese_restaurant"));
        JSONArray resultsJson = new JSONArray().put(placeJson);
        JSONObject responseJson = new JSONObject().put("results", resultsJson);

        when(searchGateways.fetchNearbyRestaurants(anyDouble(), anyDouble(), anyInt())).thenReturn(responseJson);

        Location expectedLocation = new Location(37.7749, -122.4194);
        Restaurant expectedRestaurant = new Restaurant("place1", "Restaurant 1", expectedLocation, "123 Main Street", DishType.CHINESE, 4.5, "");
        when(inputAdapter.adaptToRestaurant(any(JSONObject.class))).thenReturn(expectedRestaurant);
        when(fetchRestaurantPhotoUrlUseCase.execute(any(JSONObject.class))).thenReturn("http://example.com/photo.jpg");
        when(findRestaurantByIdUseCase.execute(anyString())).thenReturn(Optional.empty());

        SearchRestaurantInput searchInput = new SearchRestaurantInput(37.7749, -122.4194, "500", DishType.CHINESE);

        List<Restaurant> restaurants = service.fetchNearbyRestaurants(searchInput, 10, 5);

        assertEquals(1, restaurants.size());
        Restaurant result = restaurants.get(0);
        assertEquals("place1", result.getRestaurantId());
        assertEquals("Restaurant 1", result.getName());
        assertEquals(expectedLocation, result.getLocation());
        assertEquals("123 Main Street", result.getAddress());
        assertEquals(4.5, result.getAverageRating());
        assertEquals("http://example.com/photo.jpg", result.getPhotoUrl());
        assertEquals(DishType.CHINESE, result.getDishType());

        verify(addRestaurantUseCase, times(1)).execute(any(Restaurant.class));
        verify(updateRestaurantUseCase, never()).execute(any(Restaurant.class));
    }
}
