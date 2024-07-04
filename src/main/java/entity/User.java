package main.java.entity;

/**
 * Represents a user of the app.
 */
public class User {
    private int userId;
    private String userName;
    private double latitude;
    private double longitude;

    /**
     * @param userId   the unique user identifier
     * @param userName the user's name
     * @param latitude the user's current or selected latitude
     * @param longitude the user's current or selected longitude
     */
    public User(int userId, String userName, double latitude, double longitude) {
        this.userId = userId;
        this.userName = userName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
