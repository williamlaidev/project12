package entity.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for latitude in a Location.
 * Ensures that the latitude is within the valid range of -90 to 90 degrees.
 */
public class LocationLatitudeValidator extends LocationValidator {

    private static final Logger logger = LoggerFactory.getLogger(LocationLatitudeValidator.class);

    /**
     * Validates the latitude of the given Location.
     *
     * @param location The Location to validate.
     * @return true if latitude is valid, false otherwise.
     */
    @Override
    public boolean check(Location location) {
        double latitude = location.getLatitude();
        if (latitude < -90 || latitude > 90) {
            logger.warn("Latitude validation failed for location: {}", location);
            return false;
        }
        return checkNext(location);
    }
}
