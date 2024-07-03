package main.entity;

/**
 * Factory class for creating User instances.
 */
public class UserFactory {

    /**
     * @param userId    the unique user identifier
     * @param userName  the user's name
     * @param latitude  the user's current or selected latitude
     * @param longitude the user's current or selected longitude
     * @return a new User object
     */
    public static User create(int userId, String userName, double latitude, double longitude) {
        return new User(userId, userName, latitude, longitude);
    }
}
