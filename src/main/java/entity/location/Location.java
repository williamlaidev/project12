package entity.location;

/**
 * Represents a geographical location with validated latitude and longitude.
 */
public class Location {
    private double latitude;
    private double longitude;

    private static final LocationValidator validatorChain;

    static {
        validatorChain = new LocationLatitudeValidator();
        validatorChain.linkWith(new LocationLongitudeValidator());
    }

    /**
     * Creates a Location with specified latitude and longitude.
     *
     * @param latitude  Latitude, between -90 and 90 degrees.
     * @param longitude Longitude, between -180 and 180 degrees.
     * @throws IllegalArgumentException if coordinates are out of range.
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid latitude or longitude.");
        }
    }

    /**
     * Gets the latitude.
     *
     * @return Latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude and validates it.
     *
     * @param latitude New latitude.
     * @throws IllegalArgumentException if out of range.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid latitude.");
        }
    }

    /**
     * Gets the longitude.
     *
     * @return Longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude and validates it.
     *
     * @param longitude New longitude.
     * @throws IllegalArgumentException if out of range.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid longitude.");
        }
    }

    /**
     * Resets latitude and longitude with validation.
     *
     * @param latitude  New latitude.
     * @param longitude New longitude.
     * @throws IllegalArgumentException if coordinates are out of range.
     */
    public void resetLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid latitude or longitude.");
        }
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 &&
                Double.compare(location.longitude, longitude) == 0;
    }
}
