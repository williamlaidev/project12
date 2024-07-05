package main.java.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "John Doe", 40.7128, -74.0062);
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void getUserId() {
        assertEquals(1, user.getUserId());
    }

    @Test
    void getUserName() {
        assertEquals("John Doe", user.getUserName());
    }

    @Test
    void getLatitude() {
        assertEquals(40.7128, user.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(-74.0062, user.getLongitude());
    }

    @Test
    void setUserId() {
        user.setUserId(2);
        assertEquals(2, user.getUserId());
    }

    @Test
    void setUserName() {
        user.setUserName("Jane Doe");
        assertEquals("Jane Doe", user.getUserName());
    }

    @Test
    void setLatitude() {
        user.setLatitude(34.0522);
        assertEquals(34.0522, user.getLatitude());
    }

    @Test
    void setLongitude() {
        user.setLongitude(-118.2437);
        assertEquals(-118.2437, user.getLongitude());
    }

    @Test
    void testToString() {
        String expected = "User{userId=1, userName='John Doe', latitude=40.7128, longitude=-74.0062}";
        assertEquals(expected, user.toString());
    }
}
