package use_case.view;

import entity.DishType;
import entity.map.Map;
import entity.map.MapFactory;
import framework.search.GoogleGeolocationService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class InitializerTest {

    private GoogleGeolocationService mockGeolocationService;
    private MapImageInteractor mockMapImageInteractor;
    private MapFactory mockMapFactory;
    private Initializer initializer;

    @BeforeEach
    void setUp() {
        mockGeolocationService = mock(GoogleGeolocationService.class);
        mockMapImageInteractor = mock(MapImageInteractor.class);
        mockMapFactory = mock(MapFactory.class);
        initializer = new Initializer(mockGeolocationService, mockMapImageInteractor, mockMapFactory);
    }

    @Test
    void testInitializeCurrentLocationSuccess() throws Exception {
        when(mockGeolocationService.getCurrentLocation()).thenReturn(new JSONObject()
                .put("location", new JSONObject()
                        .put("lat", 40.7128)
                        .put("lng", -74.0060)
                )
        );
        Map mockMap = mock(Map.class);
        when(mockMapFactory.createMap(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(mockMap);
        when(mockMapImageInteractor.fetchAndSaveMapImage(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(true);

        initializer.initializeCurrentLocation();

        assertEquals(40.7128, initializer.getLatitude());
        assertEquals(-74.0060, initializer.getLongitude());
        assertNotNull(initializer.getMap());
    }

    @Test
    void testInitializeCurrentLocationFailure() throws Exception {
        when(mockGeolocationService.getCurrentLocation()).thenThrow(new RuntimeException("Geolocation service failed"));

        assertThrows(RuntimeException.class, () -> initializer.initializeCurrentLocation());
    }

    @Test
    void testGetDishTypes() {
        DishType[] dishTypes = DishType.values();
        String[] expectedDishTypes = new String[dishTypes.length];

        for (int i = 0; i < dishTypes.length; i++) {
            expectedDishTypes[i] = dishTypes[i].name();
        }

        String[] actualDishTypes = initializer.getDishTypes();

        assertArrayEquals(expectedDishTypes, actualDishTypes);
    }

    @Test
    void testGetLatitude() throws Exception {
        when(mockGeolocationService.getCurrentLocation()).thenReturn(new JSONObject()
                .put("location", new JSONObject()
                        .put("lat", 40.7128)
                        .put("lng", -74.0060)
                )
        );
        when(mockMapFactory.createMap(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(mock(Map.class));
        when(mockMapImageInteractor.fetchAndSaveMapImage(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(true);

        initializer.initializeCurrentLocation();

        assertEquals(40.7128, initializer.getLatitude());
    }

    @Test
    void testGetLongitude() throws Exception {
        when(mockGeolocationService.getCurrentLocation()).thenReturn(new JSONObject()
                .put("location", new JSONObject()
                        .put("lat", 40.7128)
                        .put("lng", -74.0060)
                )
        );
        when(mockMapFactory.createMap(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(mock(Map.class));
        when(mockMapImageInteractor.fetchAndSaveMapImage(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(true);

        initializer.initializeCurrentLocation();

        assertEquals(-74.0060, initializer.getLongitude());
    }

    @Test
    void testMapCreation() throws Exception {
        when(mockGeolocationService.getCurrentLocation()).thenReturn(new JSONObject()
                .put("location", new JSONObject()
                        .put("lat", 40.7128)
                        .put("lng", -74.0060)
                )
        );
        Map mockMap = mock(Map.class);
        when(mockMapFactory.createMap(eq(40.7128), eq(-74.0060), eq(15), eq(400), eq(400))).thenReturn(mockMap);
        when(mockMapImageInteractor.fetchAndSaveMapImage(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(true);

        initializer.initializeCurrentLocation();

        verify(mockMapFactory).createMap(eq(40.7128), eq(-74.0060), eq(15), eq(400), eq(400));
    }
}
