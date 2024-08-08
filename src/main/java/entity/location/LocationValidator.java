package entity.location;

public abstract class LocationValidator {
    private LocationValidator nextValidator;

    public LocationValidator linkWith(LocationValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public abstract boolean check(Location location);

    protected boolean checkNext(Location location) {
        if (nextValidator == null) {
            return true;
        }
        return nextValidator.check(location);
    }
}
