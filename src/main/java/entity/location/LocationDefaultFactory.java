package entity.location;

public class LocationDefaultFactory implements LocationFactory {

    @Override
    public Location createLocation(double latitude, double longitude) {
        return new Location(latitude, longitude);
    }
}
