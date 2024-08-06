package interface_adapter.data;

import entity.restaurant.Restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RestaurantDataAdapter {
     Restaurant adaptToRestaurant(ResultSet rs) throws SQLException;
}