package use_case.UI;

import entity.DishType;

/**
 * Represents the input parameters for a search query to find restaurants.
 */
public class SearchInput {
    private final double latitude;
    private final double longitude;
    private final String distance;
    private final DishType dishType;

    /**
     * Constructs a {@code SearchInput} instance with the specified parameters.
     *
     * @param latitude  the latitude of the search center, expressed in degrees
     * @param longitude the longitude of the search center, expressed in degrees
     * @param distance  the search radius around the location, expressed in meters
     * @param dishType  the type of dish to filter the search results by
     */
    public SearchInput(double latitude, double longitude, String distance, DishType dishType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.dishType = dishType;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDistance() {
        return distance;
    }

    public DishType getDishType() {
        return dishType;
    }

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
