package entity.location;

/**
 * Abstract base class for Location validators.
 * Provides a chain of responsibility pattern for validating Location instances.
 */
public abstract class LocationValidator {
    private LocationValidator nextValidator;

    /**
     * Links this validator to the next validator in the chain.
     *
     * @param nextValidator The next LocationValidator to link.
     * @return The next LocationValidator in the chain.
     */
    public LocationValidator linkWith(LocationValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    /**
     * Abstract method to be implemented by concrete validators.
     * Validates the given Location.
     *
     * @param location The Location to validate.
     * @return true if the location is valid, false otherwise.
     */
    public abstract boolean check(Location location);

    /**
     * Checks the next validator in the chain.
     *
     * @param location The Location to validate.
     * @return true if the next validator (if any) validates the location.
     */
    protected boolean checkNext(Location location) {
        if (nextValidator == null) {
            return true;
        }
        return nextValidator.check(location);
    }
}
