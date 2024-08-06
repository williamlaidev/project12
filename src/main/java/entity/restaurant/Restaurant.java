package entity.restaurant;

import entity.DishType;
import entity.location.Location;

import java.util.Objects;

public class Restaurant {
    private final String restaurantId;
    private String name;
    private Location location;
    private String address;
    private DishType dishType;
    private double averageRating;
    private String photoUrl;

    private static final RestaurantValidator validatorChain;

    static {
        validatorChain = new RestaurantIdValidator();
        validatorChain.linkWith(new RestaurantNameValidator())
                .linkWith(new RestaurantAddressValidator())
                .linkWith(new RestaurantAverageRatingValidator());
    }

    public Restaurant(String restaurantId, String name, Location location, String address, DishType dishType, double averageRating, String photoUrl) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.address = address;
        this.dishType = dishType;
        this.averageRating = averageRating;
        this.photoUrl = photoUrl == null ? "" : photoUrl;

        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid restaurant data.");
        }
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
        this.name = name;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid restaurant name.");
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAddress(String address) {
        this.address = address;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid restaurant address.");
        }
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid restaurant average rating.");
        }
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
                ", photoUrl='" + photoUrl + '\'' +
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
}
