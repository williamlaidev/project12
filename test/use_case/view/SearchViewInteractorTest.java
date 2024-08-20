package use_case.view;

import entity.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search.SearchRestaurantInput;

import static org.mockito.Mockito.*;

public class SearchViewInteractorTest {

    private Map mockMap;
    private SearchViewInteractor searchViewInteractor;

    @BeforeEach
    public void setUp() {
        mockMap = mock(Map.class);
        searchViewInteractor = new SearchViewInteractor(mockMap);
    }

    @Test
    public void testAdjustZoomLevel() {
        int initialZoom = 10;
        int zoomChange = 2;
        when(mockMap.getZoomLevel()).thenReturn(initialZoom);

        searchViewInteractor.adjustZoomLevel(zoomChange);

        int expectedZoom = initialZoom + zoomChange;
        verify(mockMap).setZoomLevel(expectedZoom);
        verify(mockMap, times(1)).setZoomLevel(expectedZoom);
    }

    @Test
    public void testAdjustCenter() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        SearchRestaurantInput input = new SearchRestaurantInput(latitude, longitude, "500");

        searchViewInteractor.adjustCenter(input);

        verify(mockMap).setCurrentLatitude(latitude);
        verify(mockMap).setCurrentLongitude(longitude);
    }
}
