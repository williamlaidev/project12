package use_case.view;

import framework.search.GoogleMapsImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapImageInteractorTest {

    private GoogleMapsImageService googleMapsImageService;
    private MapImageInteractor mapImageInteractor;

    @BeforeEach
    public void setUp() {
        googleMapsImageService = mock(GoogleMapsImageService.class);
        mapImageInteractor = new MapImageInteractor(googleMapsImageService);
    }

    @Test
    public void testFetchAndSaveMapImageSuccess() throws IOException {
        byte[] imageData = new byte[]{1, 2, 3, 4, 5};
        when(googleMapsImageService.getMapImage(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenReturn(imageData);

        boolean success = mapImageInteractor.fetchAndSaveMapImage(37.7749, -122.4194, 12, 800, 600);

        assertTrue(success);
        verify(googleMapsImageService, times(1)).getMapImage(37.7749, -122.4194, 12, 800, 600);
    }

    @Test
    public void testFetchAndSaveMapImageFailure() throws IOException {
        when(googleMapsImageService.getMapImage(anyDouble(), anyDouble(), anyInt(), anyInt(), anyInt())).thenThrow(new IOException("Failed to fetch map image"));

        boolean success = mapImageInteractor.fetchAndSaveMapImage(37.7749, -122.4194, 12, 800, 600);

        assertFalse(success);
        verify(googleMapsImageService, times(1)).getMapImage(37.7749, -122.4194, 12, 800, 600);
    }
}
