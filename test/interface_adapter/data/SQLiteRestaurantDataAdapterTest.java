package interface_adapter.data;

import entity.restaurant.Restaurant;
import entity.restaurant.RestaurantDefaultFactory;
import entity.location.Location;
import entity.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SQLiteRestaurantDataAdapterTest {

    private SQLiteRestaurantDataAdapter adapter;

    @BeforeEach
    public void setUp() {
        RestaurantDefaultFactory restaurantFactory = new RestaurantDefaultFactory();
        adapter = new SQLiteRestaurantDataAdapter();
    }

    @Test
    public void testAdaptToRestaurant() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getString("restaurantId")).thenReturn("1");
        when(rs.getString("name")).thenReturn("Test Restaurant");
        when(rs.getDouble("latitude")).thenReturn(37.7749);
        when(rs.getDouble("longitude")).thenReturn(-122.4194);
        when(rs.getString("address")).thenReturn("123 Main St");
        when(rs.getString("dishType")).thenReturn("chinese_restaurant");
        when(rs.getDouble("averageRating")).thenReturn(4.5);
        when(rs.getString("photoUrl")).thenReturn("http://example.com/photo.jpg");

        Restaurant restaurant = adapter.adaptToRestaurant(rs);

        assertNotNull(restaurant);
        assertEquals("1", restaurant.getRestaurantId());
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals(new Location(37.7749, -122.4194), restaurant.getLocation());
        assertEquals("123 Main St", restaurant.getAddress());
        assertEquals(DishType.CHINESE, restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }

    @Test
    public void testAdaptToRestaurantWithNullDishType() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getString("restaurantId")).thenReturn("1");
        when(rs.getString("name")).thenReturn("Test Restaurant");
        when(rs.getDouble("latitude")).thenReturn(37.7749);
        when(rs.getDouble("longitude")).thenReturn(-122.4194);
        when(rs.getString("address")).thenReturn("123 Main St");
        when(rs.getString("dishType")).thenReturn(null);
        when(rs.getDouble("averageRating")).thenReturn(4.5);
        when(rs.getString("photoUrl")).thenReturn("http://example.com/photo.jpg");

        Restaurant restaurant = adapter.adaptToRestaurant(rs);

        assertNotNull(restaurant);
        assertEquals("1", restaurant.getRestaurantId());
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals(new Location(37.7749, -122.4194), restaurant.getLocation());
        assertEquals("123 Main St", restaurant.getAddress());
        assertNull(restaurant.getDishType());
        assertEquals(4.5, restaurant.getAverageRating());
        assertEquals("http://example.com/photo.jpg", restaurant.getPhotoUrl());
    }

    @Test
    public void testAdaptToRestaurantWithSQLException() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getString(anyString())).thenThrow(new SQLException("Test SQL exception"));

        assertThrows(SQLException.class, () -> adapter.adaptToRestaurant(rs));
    }
}
