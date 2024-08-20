package use_case.search;

import entity.DishType;

/**
 * Represents input parameters for searching restaurants.
 * Contains details such as location, search radius, and dish type.
 */
public class SearchRestaurantInput {
    private final double latitude;
    private final double longitude;
    private final String distance;
    private final DishType dishType;

    /**
     * Constructs an instance with the specified parameters.
     *
     * @param latitude  the latitude of the search center, in degrees.
     * @param longitude the longitude of the search center, in degrees.
     * @param distance  the search radius, in meters.
     * @param dishType  the type of dish to filter by.
     */
    public SearchRestaurantInput(double latitude, double longitude, String distance, String dishType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.dishType = DishType.valueOf(dishType.toUpperCase());
    }

    public SearchRestaurantInput(double latitude, double longitude, String distance, DishType dishType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.dishType = dishType;
    }

    public SearchRestaurantInput(double latitude, double longitude, String distance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.dishType = null;
    }

    /**
     * Returns the latitude of the search center.
     *
     * @return the latitude, in degrees.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of the search center.
     *
     * @return the longitude, in degrees.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the search radius.
     *
     * @return the distance, in meters.
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Returns the dish type to filter by.
     *
     * @return the dish type.
     */
    public DishType getDishType() {
        return dishType;
    }

    @Override
    public String toString() {
        return "SearchRestaurantInput{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance='" + distance + '\'' +
                ", dishType=" + dishType +
                '}';
    }
}
