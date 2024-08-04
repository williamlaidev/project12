package entity;

import java.util.Objects;

public class Restaurant {
    private final String restaurantId;
    private String name;
    private Location location;
    private String address;
    private DishType dishType;
    private double averageRating;
    private String photoUrl;

    public Restaurant(String restaurantId, String name, Location location, String address, DishType dishType, double averageRating, String photoUrl) {
        validateRestaurantId(restaurantId);
        validateName(name);
        validateAddress(address);
        validateAverageRating(averageRating);

        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.address = address;
        this.dishType = dishType;
        this.averageRating = averageRating;
        this.photoUrl = photoUrl == null ? "" : photoUrl;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public DishType getDishType() {
        return dishType;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public void setAverageRating(double averageRating) {
        validateAverageRating(averageRating);
        this.averageRating = averageRating;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? "" : photoUrl;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId='" + restaurantId + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", address='" + address + '\'' +
                ", dishType=" + dishType +
                ", averageRating=" + averageRating +
                ", photoUrl='" + photoUrl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Double.compare(that.averageRating, averageRating) == 0 &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(location, that.location) &&
                Objects.equals(address, that.address) &&
                Objects.equals(dishType, that.dishType) &&
                Objects.equals(photoUrl, that.photoUrl);
    }

    private void validateRestaurantId(String restaurantId) {
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant ID cannot be null or empty.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be null or empty.");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant address cannot be null or empty.");
        }
    }

    private void validateAverageRating(double averageRating) {
        if (averageRating < 0 || averageRating > 5) {
            throw new IllegalArgumentException("Average rating must be between 0 and 5.");
        }
    }
}
