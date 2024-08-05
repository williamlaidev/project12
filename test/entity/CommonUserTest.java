package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUserTest {

    private CommonUser user;
    private Location location;
    private LocalDateTime creationTime;

    @BeforeEach
    public void setUp() {
        location = new Location(49.2827, -123.1207);
        creationTime = LocalDateTime.now();
        user = new CommonUser(1, "Jenny", location, creationTime);
    }

    @AfterEach
    public void tearDown() {
        user = null;
        location = null;
        creationTime = null;
    }

    @Test
    public void testValidCommonUserCreation() {
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("Jenny", user.getName());
        assertEquals(location, user.getLocation());
        assertEquals(creationTime, user.getCreationTime());
        assertTrue(user.getSavedRestaurantList().isEmpty());
    }

    @Test
    public void testInvalidName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                new CommonUser(2, "", location, creationTime);
            }
        });
        assertEquals("User name cannot be null or empty.", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                new CommonUser(3, null, location, creationTime);
            }
        });
        assertEquals("User name cannot be null or empty.", thrown.getMessage());
    }

    @Test
    public void testAddSavedRestaurantList() {
        RestaurantList restaurantList = new RestaurantList(1, user, "My Favorite Restaurants", "A list of my favorite restaurants.", new ArrayList<>());
        user.addSavedRestaurantList(restaurantList);
        assertEquals(1, user.getSavedRestaurantList().size());
        assertTrue(user.getSavedRestaurantList().contains(restaurantList));
    }

    @Test
    public void testRemoveSavedRestaurantList() {
        RestaurantList restaurantList = new RestaurantList(1, user, "My Favorite Restaurants", "A list of my favorite restaurants.", new ArrayList<>());
        user.addSavedRestaurantList(restaurantList);
        assertEquals(1, user.getSavedRestaurantList().size());
        assertTrue(user.getSavedRestaurantList().contains(restaurantList));

        user.removeSavedRestaurantList(restaurantList);
        assertTrue(user.getSavedRestaurantList().isEmpty());
        assertFalse(user.getSavedRestaurantList().contains(restaurantList));
    }

    @Test
    public void testResetLocation() {
        Location newLocation = new Location(48.4284, -123.3656);
        user.resetLocation(newLocation);
        assertEquals(newLocation, user.getLocation());
    }

    @Test
    public void testToString() {
        String expectedString = "CommonUser{" +
                "name='Jenny'" +
                ", location=" + location +
                ", creationTime=" + creationTime +
                ", savedRestaurantList=[]" +
                '}';
        assertEquals(expectedString, user.toString());
    }
}
