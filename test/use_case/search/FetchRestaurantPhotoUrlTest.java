package use_case.search;

import domain.RestaurantPhotoService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FetchRestaurantPhotoUrlTest {

    private RestaurantPhotoService restaurantPhotoService;
    private FetchRestaurantPhotoUrl fetchRestaurantPhotoUrl;

    @BeforeEach
    public void setUp() {
        restaurantPhotoService = mock(RestaurantPhotoService.class);
        fetchRestaurantPhotoUrl = new FetchRestaurantPhotoUrl(restaurantPhotoService);
    }

    @Test
    public void testExecute() {
        JSONObject placeJson = new JSONObject().put("photo_reference", "sample_photo_reference");
        String expectedUrl = "http://example.com/photo.jpg";

        when(restaurantPhotoService.fetchPhotoUrl(placeJson)).thenReturn(expectedUrl);

        String result = fetchRestaurantPhotoUrl.execute(placeJson);
        assertEquals(expectedUrl, result);
    }
}
