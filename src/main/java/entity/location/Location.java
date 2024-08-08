package entity.location;

public class Location {
    private double latitude;
    private double longitude;

    private static final LocationValidator validatorChain;

    static {
        validatorChain = new LocationLatitudeValidator();
        validatorChain.linkWith(new LocationLongitudeValidator());
    }

    /**
     * Constructs a Location object with the specified latitude and longitude.
     *
     * @param latitude  The latitude coordinate, must be between -90 and 90 degrees.
     * @param longitude The longitude coordinate, must be between -180 and 180 degrees.
     * @throws IllegalArgumentException if latitude or longitude is out of valid range.
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid latitude or longitude.");
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid latitude.");
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
        if (!validatorChain.check(this)) {
            throw new IllegalArgumentException("Invalid longitude.");
        }
    }

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
