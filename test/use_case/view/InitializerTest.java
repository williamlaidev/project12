package use_case.view;

import entity.map.Map;
import entity.map.MapFactory;
import framework.search.GoogleGeolocationService;
import interface_adapter.view.SearchState;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InitializerTest {

    private GoogleGeolocationService geolocationService;
    private MapImageInteractor mapImageInteractor;
    private MapFactory mapFactory;
    private Initializer initializer;
    private SearchState searchState;

    @BeforeEach
    public void setUp() {
        geolocationService = mock(GoogleGeolocationService.class);
        mapImageInteractor = mock(MapImageInteractor.class);
        mapFactory = mock(MapFactory.class);
        searchState = mock(SearchState.class);
        initializer = new Initializer(geolocationService, mapImageInteractor, mapFactory, searchState);
    }

    @Test
    public void testInitializeCurrentLocationSuccess() throws Exception {
        JSONObject locationJson = new JSONObject()
                .put("location", new JSONObject()
                        .put("lat", 37.7749)
                        .put("lng", -122.4194));
        when(geolocationService.getCurrentLocation()).thenReturn(locationJson);
        when(mapImageInteractor.fetchAndSaveMapImage(37.7749, -122.4194, 15, 400, 400)).thenReturn(true);
        Map mockMap = mock(Map.class);
        when(mapFactory.createMap(37.7749, -122.4194, 15, 400, 400)).thenReturn(mockMap);

        initializer.initializeCurrentLocation();

        assertEquals(37.7749, initializer.getLatitude());
        assertEquals(-122.4194, initializer.getLongitude());
        assertEquals(mockMap, initializer.getMap());
    }

    @Test
    public void testInitializeCurrentLocationFailure() throws Exception {
        JSONObject locationJson = new JSONObject()
                .put("location", new JSONObject()
                        .put("lat", 37.7749)
                        .put("lng", -122.4194));
        when(geolocationService.getCurrentLocation()).thenReturn(locationJson);
        when(mapImageInteractor.fetchAndSaveMapImage(37.7749, -122.4194, 15, 400, 400)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            initializer.initializeCurrentLocation();
        });

        assertEquals("Failed to fetch and save the map image.", exception.getMessage());
    }
}
