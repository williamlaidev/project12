package entity;

import java.util.Objects;

/**
 * Represents a dining establishment.
 */
public class Restaurant {
    private String restaurantId;
    private String name;
    private Location location;
    private String address;
    private DishType dishType;
    private double averageRating;
    private String photoUrl;
    private String summarizedReview;

    /**
     * Constructs a new Restaurant with the specified details.
     *
     * @param restaurantId the ID of the restaurant
     * @param name the name of the restaurant
     * @param location the location of the restaurant
     * @param address the address of the restaurant
     * @param dishType the type of dishes the restaurant serves
     * @param averageRating the average rating of the restaurant
     * @param photoUrl the URL of the restaurant's photo (optional)
     * @param summarizedReview a summarized review of the restaurant (optional)
     * @throws IllegalArgumentException if any parameter constraints are violated
     */
    public Restaurant(String restaurantId, String name, Location location, String address, DishType dishType, double averageRating, String photoUrl, String summarizedReview) {
        validateRestaurantId(restaurantId); // Ensure this is validated
        validateName(name);
        validateAddress(address);
        validateAverageRating(averageRating);

        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.address = address;
        this.dishType = dishType;
        this.averageRating = averageRating;
        this.photoUrl = photoUrl;
        this.summarizedReview = (summarizedReview == null) ? "" : summarizedReview;
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

    public String getSummarizedReview() {
        return summarizedReview;
    }

    public void setRestaurantId(String restaurantId) {
        validateRestaurantId(restaurantId); // Ensure this is validated
        this.restaurantId = restaurantId;
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
        this.photoUrl = (photoUrl == null) ? "" : photoUrl;
    }

    public void setSummarizedReview(String summarizedReview) {
        this.summarizedReview = (summarizedReview == null) ? "" : summarizedReview;
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
                ", summarizedReview='" + summarizedReview + '\'' +
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
                dishType == that.dishType &&
                Objects.equals(photoUrl, that.photoUrl) &&
                Objects.equals(summarizedReview, that.summarizedReview);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, name, location, address, dishType, averageRating, photoUrl, summarizedReview);
    }

    // Private helper methods for validation
    private void validateRestaurantId(String restaurantId) {
        try {
            int id = Integer.parseInt(restaurantId);
            if (id < 0) {
                throw new IllegalArgumentException("Restaurant ID cannot be negative.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Restaurant ID must be a valid integer.");
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
