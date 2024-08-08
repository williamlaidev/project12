package entity.location;

public class LocationLongitudeValidator extends LocationValidator {

    @Override
    public boolean check(Location location) {
        double longitude = location.getLongitude();
        if (longitude < -180 || longitude > 180) {
            System.out.println("Longitude validation failed.");
            return false;
        }
        return checkNext(location);
    }
}
