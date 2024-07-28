package integration;

import data_access.RestaurantDataAccess;
import domain.RestaurantRepository;
import entity.DishType;
import entity.Location;
import entity.Restaurant;
import entity.Review;
import framework.data.JsonRestaurantDataAccess;
import interface_adapter.data.InMemoryRestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.data.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantJsonIntegrationTest {

    private static final String JSON_FILE_PATH = "src/resources/data/restaurants.json";
    private RestaurantRepository repository;

    @BeforeEach
    public void setUp() {
        RestaurantDataAccess dataAccess = new JsonRestaurantDataAccess();
        repository = new InMemoryRestaurantRepository(dataAccess);

        File jsonFile = new File(JSON_FILE_PATH);
        if (jsonFile.exists()) {
            if (!jsonFile.delete()) {
                fail("Failed to delete existing JSON file: " + JSON_FILE_PATH);
            }
        }
    }

    @AfterEach
    public void tearDown() {
        File jsonFile = new File(JSON_FILE_PATH);
        if (jsonFile.exists()) {
            if (!jsonFile.delete()) {
                fail("Failed to delete JSON file: " + JSON_FILE_PATH);
            }
        }
    }

    @Test
    public void testAddRestaurant() {
        AddRestaurant addRestaurantUseCase = new AddRestaurant(repository);
        Location location = new Location(40.7128, -74.0060); // Valid latitude and longitude
        Restaurant restaurant = new Restaurant(
                "1",
                "Test Restaurant",
                location,
                "123 Test Street",
                DishType.ITALIAN,
                4.5,
                "http://test.com/photo.jpg",
                List.of(new Review("1", "author1", "content1", false)),
                new Review("1", "author1", "content1", true)
        );
        boolean result = addRestaurantUseCase.execute(restaurant);
        assertTrue(result, "Failed to add restaurant with ID: " + restaurant.getRestaurantId());

        // Verify restaurant is added
        GetRestaurantById getRestaurantByIdUseCase = new GetRestaurantById(repository);
        Optional<Restaurant> retrievedRestaurant = getRestaurantByIdUseCase.execute("1");
        assertTrue(retrievedRestaurant.isPresent(), "Restaurant with ID '1' was not found.");
        assertEquals(restaurant, retrievedRestaurant.get(), "Added restaurant does not match retrieved restaurant.");

        // Try adding the same restaurant again and expect failure
        boolean duplicateResult = addRestaurantUseCase.execute(restaurant);
        assertFalse(duplicateResult, "Adding a restaurant with a duplicate ID should fail.");
    }

    @Test
    public void testGetRestaurantByLocation() {
        AddRestaurant addRestaurantUseCase = new AddRestaurant(repository);
        Location location = new Location(40.7128, -74.0060); // Valid latitude and longitude
        Restaurant restaurant = new Restaurant(
                "1",
                "Test Restaurant",
                location,
                "123 Test Street",
                DishType.ITALIAN,
                4.5,
                "http://test.com/photo.jpg",
                List.of(new Review("1", "author1", "content1", false)),
                new Review("1", "author1", "content1", true)
        );
        addRestaurantUseCase.execute(restaurant);

        GetRestaurantByLocation getRestaurantByLocationUseCase = new GetRestaurantByLocation(repository);
        Optional<Restaurant> retrievedRestaurant = getRestaurantByLocationUseCase.execute(location);
        assertTrue(retrievedRestaurant.isPresent(), "Restaurant at the location was not found.");
        assertEquals(restaurant, retrievedRestaurant.get(), "Retrieved restaurant does not match the added restaurant.");

        // Try retrieving a restaurant at a location where none exists
        Location nonExistentLocation = new Location(35.0000, -90.0000);
        Optional<Restaurant> missingRestaurant = getRestaurantByLocationUseCase.execute(nonExistentLocation);
        assertFalse(missingRestaurant.isPresent(), "No restaurant should be found at this non-existent location.");
    }

    @Test
    public void testUpdateRestaurant() {
        AddRestaurant addRestaurantUseCase = new AddRestaurant(repository);
        Location location = new Location(40.7128, -74.0060); // Valid latitude and longitude
        Restaurant restaurant = new Restaurant(
                "1",
                "Test Restaurant",
                location,
                "123 Test Street",
                DishType.ITALIAN,
                4.5,
                "http://test.com/photo.jpg",
                List.of(new Review("1", "author1", "content1", false)),
                new Review("1", "author1", "content1", true)
        );
        addRestaurantUseCase.execute(restaurant);

        UpdateRestaurant updateRestaurantUseCase = new UpdateRestaurant(repository);
        Location updatedLocation = new Location(40.7306, -73.9352); // Valid latitude and longitude
        Restaurant updatedRestaurant = new Restaurant(
                "1",
                "Updated Restaurant",
                updatedLocation,
                "456 Updated Street",
                DishType.MEXICAN,
                4.8,
                "http://test.com/photo_updated.jpg",
                List.of(new Review("1", "author1", "content1", false)),
                new Review("1", "author1", "content1", true)
        );
        boolean updateResult = updateRestaurantUseCase.execute(updatedRestaurant);
        assertTrue(updateResult, "Failed to update restaurant with ID: " + updatedRestaurant.getRestaurantId());

        // Verify restaurant is updated
        GetRestaurantById getRestaurantByIdUseCase = new GetRestaurantById(repository);
        Optional<Restaurant> retrievedRestaurant = getRestaurantByIdUseCase.execute("1");
        assertTrue(retrievedRestaurant.isPresent(), "Restaurant with ID '1' was not found.");
        assertEquals(updatedRestaurant, retrievedRestaurant.get(), "Updated restaurant does not match retrieved restaurant.");

        // Try updating a non-existent restaurant
        Restaurant nonExistentRestaurant = new Restaurant(
                "2",
                "Non-Existent Restaurant",
                updatedLocation,
                "789 Non-Existent Street",
                DishType.CHINESE,
                4.5,
                "http://test.com/photo_non_existent.jpg",
                List.of(new Review("2", "author1", "content1", false)),
                new Review("2", "author1", "content1", true)
        );
        boolean nonExistentUpdateResult = updateRestaurantUseCase.execute(nonExistentRestaurant);
        assertFalse(nonExistentUpdateResult, "Updating a non-existent restaurant should fail.");
    }

    @Test
    public void testGetAllRestaurants() {
        AddRestaurant addRestaurantUseCase = new AddRestaurant(repository);
        Location location1 = new Location(40.7128, -74.0060); // Valid latitude and longitude
        Location location2 = new Location(34.0522, -118.2437); // Valid latitude and longitude
        Restaurant restaurant1 = new Restaurant(
                "1",
                "Test Restaurant 1",
                location1,
                "123 Test Street",
                DishType.ITALIAN,
                4.5,
                "http://test.com/photo1.jpg",
                List.of(new Review("1", "author1", "content1", false)),
                new Review("1", "author1", "content1", true)
        );
        Restaurant restaurant2 = new Restaurant(
                "2",
                "Test Restaurant 2",
                location2,
                "456 Another Street",
                DishType.MEXICAN,
                4.7,
                "http://test.com/photo2.jpg",
                List.of(new Review("2", "author1", "content1", false)),
                new Review("2", "author1", "content1", true)
        );
        addRestaurantUseCase.execute(restaurant1);
        addRestaurantUseCase.execute(restaurant2);

        GetAllRestaurants getAllRestaurantsUseCase = new GetAllRestaurants(repository);
        List<Restaurant> allRestaurants = getAllRestaurantsUseCase.execute();
        assertEquals(2, allRestaurants.size(), "The number of retrieved restaurants does not match the expected value.");
        assertTrue(allRestaurants.contains(restaurant1), "Restaurant with ID '1' is missing from the list of all restaurants.");
        assertTrue(allRestaurants.contains(restaurant2), "Restaurant with ID '2' is missing from the list of all restaurants.");
    }

    @Test
    public void testDeleteRestaurantById() {
        AddRestaurant addRestaurantUseCase = new AddRestaurant(repository);
        Location location = new Location(40.7128, -74.0060); // Valid latitude and longitude
        Restaurant restaurant = new Restaurant(
                "1",
                "Test Restaurant",
                location,
                "123 Test Street",
                DishType.ITALIAN,
                4.5,
                "http://test.com/photo.jpg",
                List.of(new Review("1", "author1", "content1", false)),
                new Review("1", "author1", "content1", true)
        );
        addRestaurantUseCase.execute(restaurant);

        DeleteRestaurantById deleteRestaurantByIdUseCase = new DeleteRestaurantById(repository);
        boolean deleteResult = deleteRestaurantByIdUseCase.execute("1");
        assertTrue(deleteResult, "Failed to delete restaurant with ID: 1");

        // Verify restaurant is deleted
        GetRestaurantById getRestaurantByIdUseCase = new GetRestaurantById(repository);
        Optional<Restaurant> retrievedRestaurant = getRestaurantByIdUseCase.execute("1");
        assertFalse(retrievedRestaurant.isPresent(), "Restaurant with ID '1' was found after deletion.");
    }

    @Test
    public void testDeleteRestaurantByName() {
        AddRestaurant addRestaurantUseCase = new AddRestaurant(repository);
        Location location = new Location(40.7128, -74.0060); // Valid latitude and longitude
        Restaurant restaurant = new Restaurant(
                "1",
                "Test Restaurant",
                location,
                "123 Test Street",
                DishType.ITALIAN,
                4.5,
                "http://test.com/photo.jpg",
                List.of(new Review("1", "author1", "content1", false)),
                new Review("1", "author1", "content1", true)
        );
        addRestaurantUseCase.execute(restaurant);

        DeleteRestaurantByName deleteRestaurantByNameUseCase = new DeleteRestaurantByName(repository);
        boolean deleteResult = deleteRestaurantByNameUseCase.execute("Test Restaurant");
        assertTrue(deleteResult, "Failed to delete restaurant with name: Test Restaurant");

        // Verify restaurant is deleted
        GetRestaurantByName getRestaurantByNameUseCase = new GetRestaurantByName(repository);
        Optional<Restaurant> retrievedRestaurant = getRestaurantByNameUseCase.execute("Test Restaurant");
        assertFalse(retrievedRestaurant.isPresent(), "Restaurant with name 'Test Restaurant' was found after deletion.");
    }
}
