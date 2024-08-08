package entity.restaurant;

import entity.DishType;
import entity.location.Location;

import java.util.Objects;

/**
 * Represents a restaurant with various attributes and validation.
 */
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

    /**
     * Constructs a Restaurant with the specified attributes.
     * Validates the restaurant data upon creation.
     *
     * @param restaurantId the unique identifier of the restaurant
     * @param name the name of the restaurant
     * @param location the location of the restaurant
     * @param address the address of the restaurant
     * @param dishType the type of dishes served by the restaurant
     * @param averageRating the average rating of the restaurant
     * @param photoUrl the URL of the restaurant's photo
     */
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

    /**
     * Returns the unique identifier of the restaurant.
     *
     * @return the restaurant ID
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * Returns the name of the restaurant.
     *
     * @return the restaurant name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location of the restaurant.
     *
     * @return the restaurant location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the address of the restaurant.
     *
     * @return the restaurant address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the type of dishes served by the restaurant.
     *
     * @return the dish type
     */
    public DishType getDishType() {
        return dishType;
    }

    /**
     * Returns the average rating of the restaurant.
     *
     * @return the average rating
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Returns the URL of the restaurant's photo.
     *
     * @return the photo URL
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * Sets the name of the restaurant and validates the updated data.
     *
     * @param name the new name of the restaurant
     * @throws IllegalArgumentException if the updated name is invalid
     */
    public void setName(String name) {
        this.name = name;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid restaurant name.");
        }
    }

    /**
     * Sets the location of the restaurant.
     *
     * @param location the new location of the restaurant
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets the address of the restaurant and validates the updated data.
     *
     * @param address the new address of the restaurant
     * @throws IllegalArgumentException if the updated address is invalid
     */
    public void setAddress(String address) {
        this.address = address;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid restaurant address.");
        }
    }

    /**
     * Sets the type of dishes served by the restaurant.
     *
     * @param dishType the new dish type
     */
    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    /**
     * Sets the average rating of the restaurant and validates the updated data.
     *
     * @param averageRating the new average rating
     * @throws IllegalArgumentException if the updated rating is invalid
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid restaurant average rating.");
        }
    }

    /**
     * Sets the URL of the restaurant's photo.
     *
     * @param photoUrl the new photo URL
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? "" : photoUrl;
    }

    /**
     * Returns a string representation of the restaurant.
     *
     * @return a string containing the restaurant's details
     */
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

    /**
     * Compares this restaurant to another object for equality.
     *
     * @param o the object to compare with
     * @return true if the objects are equal; false otherwise
     */
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
