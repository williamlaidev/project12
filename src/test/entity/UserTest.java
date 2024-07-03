package test.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.entity.User;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize a User instance before each test
        int userId = 1;
        String userName = "John Doe";
        double latitude = 37.7749;  // Example latitude for San Francisco
        double longitude = -122.4194;  // Example longitude for San Francisco

        user = new User(userId, userName, latitude, longitude);
    }

    @Test
    public void testGetters() {
        assertEquals(1, user.getUserId());
        assertEquals("John Doe", user.getUserName());
        assertEquals(37.7749, user.getLatitude());
        assertEquals(-122.4194, user.getLongitude());
    }

    @Test
    public void testSetters() {
        user.setUserId(2);
        user.setUserName("Jane Smith");
        user.setLatitude(40.7128);  // Example latitude for New York City
        user.setLongitude(-74.0060);  // Example longitude for New York City

        assertEquals(2, user.getUserId());
        assertEquals("Jane Smith", user.getUserName());
        assertEquals(40.7128, user.getLatitude());
        assertEquals(-74.0060, user.getLongitude());
    }

    @Test
    public void testToString() {
        String expected = "User{userId=1, userName='John Doe', latitude=37.7749, longitude=-122.4194}";
        assertEquals(expected, user.toString());
    }
}
