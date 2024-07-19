package local_data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RestaurantDAOImplTest {

    private RestaurantDAOImpl restaurantDAO;
    private RestaurantManager restaurantManager;

    @Before
    public void setUp() {
        restaurantManager = mock(RestaurantManager.class);
        restaurantDAO = new RestaurantDAOImpl();
    }

    @Test
    public void testAddRestaurant() {
        JSONObject newRestaurant = new JSONObject();
        newRestaurant.put("name", "Test Restaurant");

        JSONArray mockData = new JSONArray();
        when(RestaurantManager.loadJson("restaurants.json")).thenReturn(mockData);

        restaurantDAO.addRestaurant(newRestaurant);

        ArgumentCaptor<JSONArray> argumentCaptor = ArgumentCaptor.forClass(JSONArray.class);
        verify(RestaurantManager).saveJson(eq("restaurants.json"), argumentCaptor.capture());

        JSONArray savedData = argumentCaptor.getValue();
        assertEquals(1, savedData.length());
        assertEquals("Test Restaurant", savedData.getJSONObject(0).getString("name"));
    }

    @Test
    public void testUpdateRestaurant() {
        JSONObject existingRestaurant = new JSONObject();
        existingRestaurant.put("name", "Existing Restaurant");
        existingRestaurant.put("rating", 3);

        JSONObject updatedRestaurant = new JSONObject();
        updatedRestaurant.put("name", "Existing Restaurant");
        updatedRestaurant.put("rating", 5);

        JSONArray mockData = new JSONArray();
        mockData.put(existingRestaurant);
        when(RestaurantManager.loadJson("restaurants.json")).thenReturn(mockData);

        restaurantDAO.updateRestaurant(updatedRestaurant);

        ArgumentCaptor<JSONArray> argumentCaptor = ArgumentCaptor.forClass(JSONArray.class);
        verify(RestaurantManager).saveJson(eq("restaurants.json"), argumentCaptor.capture());

        JSONArray savedData = argumentCaptor.getValue();
        assertEquals(1, savedData.length());
        assertEquals(5, savedData.getJSONObject(0).getInt("rating"));
    }

    @Test
    public void testDeleteRestaurant() {
        JSONObject restaurantToDelete = new JSONObject();
        restaurantToDelete.put("name", "Restaurant To Delete");

        JSONObject remainingRestaurant = new JSONObject();
        remainingRestaurant.put("name", "Remaining Restaurant");

        JSONArray mockData = new JSONArray();
        mockData.put(restaurantToDelete);
        mockData.put(remainingRestaurant);
        when(RestaurantManager.loadJson("restaurants.json")).thenReturn(mockData);

        restaurantDAO.deleteRestaurant(restaurantToDelete);

        ArgumentCaptor<JSONArray> argumentCaptor = ArgumentCaptor.forClass(JSONArray.class);
        verify(RestaurantManager).saveJson(eq("restaurants.json"), argumentCaptor.capture());

        JSONArray savedData = argumentCaptor.getValue();
        assertEquals(1, savedData.length());
        assertEquals("Remaining Restaurant", savedData.getJSONObject(0).getString("name"));
    }
}
