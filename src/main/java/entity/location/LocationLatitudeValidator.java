package entity.location;

public class LocationLatitudeValidator extends LocationValidator {

    @Override
    public boolean check(Location location) {
        double latitude = location.getLatitude();
        if (latitude < -90 || latitude > 90) {
            System.out.println("Latitude validation failed.");
            return false;
        }
        return checkNext(location);
    }
}
