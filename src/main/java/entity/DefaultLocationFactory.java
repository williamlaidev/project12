package entity;

public class DefaultLocationFactory implements LocationFactory {

    @Override
    public Location createLocation(double latitude, double longitude) {
        return new Location(latitude, longitude);
    }
}
