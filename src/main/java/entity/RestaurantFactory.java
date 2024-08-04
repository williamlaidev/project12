package entity;

public interface RestaurantFactory {
    Restaurant createRestaurant(String restaurantId, String name, Location location, String address, DishType dishType, double averageRating, String photoUrl);
}
