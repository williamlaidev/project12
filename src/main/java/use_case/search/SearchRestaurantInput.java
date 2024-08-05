package use_case.search;

import entity.DishType;

/**
 * Represents the input parameters for a search query to find restaurants.
 * This class encapsulates the details of the search criteria such as the location,
 * search radius, and dish type, which can be used by the use case layer to perform the search.
 */
public class SearchRestaurantInput {
    private final double latitude;
    private final double longitude;
    private final String distance;
    private final DishType dishType;

    /**
     * Constructs a {@code SearchRestaurantInput} instance with the specified parameters.
     *
     * @param latitude  the latitude of the search center, expressed in degrees
     * @param longitude the longitude of the search center, expressed in degrees
     * @param distance  the search radius around the location, expressed in meters
     * @param dishType  the type of dish to filter the search results by
     */
    public SearchRestaurantInput(double latitude, double longitude, String distance, DishType dishType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.dishType = dishType;
    }

    /**
     * Returns the latitude of the search center.
     *
     * @return the latitude, expressed in degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of the search center.
     *
     * @return the longitude, expressed in degrees
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the search radius around the location.
     *
     * @return the distance, expressed in meters
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Returns the type of dish to filter the search results by.
     *
     * @return the dish type
     */
    public DishType getDishType() {
        return dishType;
    }

    /**
     * Returns a string representation of the SearchInput.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "SearchInput{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance='" + distance + '\'' +
                ", dishType=" + dishType +
                '}';
    }
}
