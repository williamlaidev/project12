package entity.location;

/**
 * Interface for creating Location instances.
 */
public interface LocationFactory {
    /**
     * Creates a Location instance with the given latitude and longitude.
     *
     * @param latitude  Latitude of the location.
     * @param longitude Longitude of the location.
     * @return A new Location instance.
     */
    Location createLocation(double latitude, double longitude);
}
