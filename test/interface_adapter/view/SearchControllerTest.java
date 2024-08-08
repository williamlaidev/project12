package interface_adapter.view;

import entity.DishType;
import entity.map.Map;
import entity.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import use_case.search.RestaurantSearchInteractor;
import use_case.search.SearchRestaurantInput;
import utils.MapCoordinateToLocation;
import utils.ZoomLevelToMeter;

import java.awt.Point;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SearchControllerTest {

    private RestaurantSearchInteractor searchInteractor;
    private Map map;
    private SearchController searchController;
    private SearchViewState searchViewState;

    @BeforeEach
    public void setUp() {
        searchInteractor = mock(RestaurantSearchInteractor.class);
        map = mock(Map.class);
        when(map.getCurrentLatitude()).thenReturn(37.7749);
        when(map.getCurrentLongitude()).thenReturn(-122.4194);
        when(map.getZoomLevel()).thenReturn(12);
        searchController = new SearchController(searchInteractor, map, 800, 600);
        searchViewState = new SearchViewState();
    }

    @Test
    public void testExecuteWithMousePosition() throws Exception {
        searchViewState.setMousePosition(new Point(400, 300));
        searchViewState.setDistance("1000");
        searchViewState.setSelectedDishType("ITALIAN");

        double[] latLng = {37.7749, -122.4194};
        try (MockedStatic<MapCoordinateToLocation> mockedStatic = mockStatic(MapCoordinateToLocation.class)) {
            mockedStatic.when(() -> MapCoordinateToLocation.convert(any(Point.class), anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt()))
                    .thenReturn(latLng);

            List<Restaurant> expectedResults = List.of(new Restaurant("1", "Italian Restaurant", null, "Address", DishType.ITALIAN, 4.5, "url"));
            when(searchInteractor.fetchNearbyRestaurants(any(SearchRestaurantInput.class), anyInt(), anyInt())).thenReturn(expectedResults);

            searchController.execute(searchViewState);

            verify(searchInteractor).fetchNearbyRestaurants(any(SearchRestaurantInput.class), eq(30), eq(10));
        }
    }

    @Test
    public void testExecuteWithoutMousePosition() throws Exception {
        searchViewState.setMousePosition(null);
        searchViewState.setDistance("500");
        searchViewState.setSelectedDishType("CHINESE");

        List<Restaurant> expectedResults = List.of(new Restaurant("2", "Chinese Restaurant", null, "Address", DishType.CHINESE, 4.5, "url"));
        when(searchInteractor.fetchNearbyRestaurants(any(SearchRestaurantInput.class), anyInt(), anyInt())).thenReturn(expectedResults);

        searchController.execute(searchViewState);

        verify(searchInteractor).fetchNearbyRestaurants(any(SearchRestaurantInput.class), eq(30), eq(10));
    }

    @Test
    public void testExecuteWithEmptyDistance() throws Exception {
        searchViewState.setMousePosition(null);
        searchViewState.setDistance("");
        searchViewState.setSelectedDishType("CHINESE");

        double zoomDistance = 1000.0;
        try (MockedStatic<ZoomLevelToMeter> mockedStatic = mockStatic(ZoomLevelToMeter.class)) {
            mockedStatic.when(() -> ZoomLevelToMeter.zoomLevelToMeter(anyInt(), anyDouble(), anyInt()))
                    .thenReturn(zoomDistance);

            List<Restaurant> expectedResults = List.of(new Restaurant("3", "Chinese Restaurant", null, "Address", DishType.CHINESE, 4.5, "url"));
            when(searchInteractor.fetchNearbyRestaurants(any(SearchRestaurantInput.class), anyInt(), anyInt())).thenReturn(expectedResults);

            searchController.execute(searchViewState);

            verify(searchInteractor).fetchNearbyRestaurants(any(SearchRestaurantInput.class), eq(30), eq(10));
        }
    }

    @Test
    public void testExecuteHandlesException() throws Exception {
        searchViewState.setMousePosition(null);
        searchViewState.setDistance("500");
        searchViewState.setSelectedDishType("CHINESE");

        when(searchInteractor.fetchNearbyRestaurants(any(SearchRestaurantInput.class), anyInt(), anyInt()))
                .thenThrow(new RuntimeException("Search error"));

        try {
            searchController.execute(searchViewState);
        } catch (RuntimeException e) {
            assertEquals("Search error", e.getMessage());
        }
    }
}
