package entity.location;

/**
 * Default implementation of the LocationFactory interface.
 * Creates new instances of Location with specified latitude and longitude.
 */
public class LocationDefaultFactory implements LocationFactory {

    /**
     * Creates a Location instance with the provided latitude and longitude.
     *
     * @param latitude  Latitude of the location.
     * @param longitude Longitude of the location.
     * @return A new Location instance.
     */
    @Override
    public Location createLocation(double latitude, double longitude) {
        return new Location(latitude, longitude);
    }
}
