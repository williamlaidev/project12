package entity.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for longitude in a Location.
 * Ensures that the longitude is within the valid range of -180 to 180 degrees.
 */
public class LocationLongitudeValidator extends LocationValidator {

    private static final Logger logger = LoggerFactory.getLogger(LocationLongitudeValidator.class);

    /**
     * Validates the longitude of the given Location.
     *
     * @param location The Location to validate.
     * @return true if longitude is valid, false otherwise.
     */
    @Override
    public boolean check(Location location) {
        double longitude = location.getLongitude();
        if (longitude < -180 || longitude > 180) {
            logger.warn("Longitude validation failed for location: {}", location);
            return false;
        }
        return checkNext(location);
    }
}
