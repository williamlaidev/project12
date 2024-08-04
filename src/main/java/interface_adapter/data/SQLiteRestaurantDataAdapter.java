package interface_adapter.data;

import entity.DefaultRestaurantFactory;
import entity.Restaurant;
import entity.Location;
import entity.DishType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteRestaurantDataAdapter implements RestaurantDataAdapter {
    private final DefaultRestaurantFactory restaurantFactory;

    public SQLiteRestaurantDataAdapter() {
        this.restaurantFactory = new DefaultRestaurantFactory();
    }

    @Override
    public Restaurant adaptToRestaurant(ResultSet rs) throws SQLException {
        Location location = new Location(rs.getDouble("latitude"), rs.getDouble("longitude"));

        String dishTypeString = rs.getString("dishType");
        DishType dishType = dishTypeString != null ? DishType.fromDishTypeString(dishTypeString) : null;

        return restaurantFactory.createRestaurant(
                rs.getString("restaurantId"),
                rs.getString("name"),
                location,
                rs.getString("address"),
                dishType,
                rs.getDouble("averageRating"),
                rs.getString("photoUrl")
        );
    }
}
