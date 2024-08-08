package use_case.search;

import domain.RestaurantPhotoService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FetchRestaurantPhotoUrlTest {

    @Mock
    private RestaurantPhotoService restaurantPhotoService;

    @InjectMocks
    private FetchRestaurantPhotoUrl fetchRestaurantPhotoUrl;

    public FetchRestaurantPhotoUrlTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        JSONObject placeJson = new JSONObject();
        placeJson.put("id", "123");

        String expectedUrl = "http://example.com/photo.jpg";
        when(restaurantPhotoService.fetchPhotoUrl(placeJson)).thenReturn(expectedUrl);

        String actualUrl = fetchRestaurantPhotoUrl.execute(placeJson);

        assertEquals(expectedUrl, actualUrl);
    }
}
