package interface_adapter.data;

import entity.restaurant.Restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Adapts SQL ResultSet to a Restaurant entity.
 */
public interface RestaurantDataAdapter {
     /**
      * Converts a ResultSet to a Restaurant.
      * @param rs The ResultSet containing restaurant data.
      * @return The Restaurant entity.
      * @throws SQLException if an SQL error occurs.
      */
     Restaurant adaptToRestaurant(ResultSet rs) throws SQLException;
}
