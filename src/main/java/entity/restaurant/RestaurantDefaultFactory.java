package entity.restaurant;

import entity.DishType;
import entity.location.Location;

/**
 * Default factory for creating {@link Restaurant} instances.
 */
public class RestaurantDefaultFactory implements RestaurantFactory {

    /**
     * Creates a new {@link Restaurant} instance with the specified attributes.
     *
     * @param restaurantId the unique identifier of the restaurant
     * @param name the name of the restaurant
     * @param location the location of the restaurant
     * @param address the address of the restaurant
     * @param dishType the type of dishes served by the restaurant
     * @param averageRating the average rating of the restaurant
     * @param photoUrl the URL of the restaurant's photo
     * @return a new {@link Restaurant} instance with the given attributes
     */
    @Override
    public Restaurant createRestaurant(String restaurantId, String name, Location location, String address, DishType dishType, double averageRating, String photoUrl) {
        return new Restaurant(restaurantId, name, location, address, dishType, averageRating, photoUrl);
    }
}
