import static org.junit.jupiter.api.Assertions.*;

import entity.DishType;
import entity.Location;
import entity.Restaurant;
import framework.JsonRestaurantDataAccess;
import interface_adapter.InMemoryRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class JsonIntegrationTest {

    private JsonRestaurantDataAccess dataAccess;
    private InMemoryRestaurantRepository repository;
    private AddRestaurant addRestaurant;
    private DeleteRestaurantById deleteRestaurantById;
    private DeleteRestaurantByName deleteRestaurantByName;
    private GetAllRestaurants getAllRestaurants;
    private GetRestaurantById getRestaurantById;
    private GetRestaurantByLocation getRestaurantByLocation;
    private GetRestaurantByName getRestaurantByName;

    @BeforeEach
    public void setUp() {
        dataAccess = Mockito.mock(JsonRestaurantDataAccess.class);
        repository = new InMemoryRestaurantRepository(dataAccess);
        addRestaurant = new AddRestaurant(repository);
        deleteRestaurantById = new DeleteRestaurantById(repository);
        deleteRestaurantByName = new DeleteRestaurantByName(repository);
        getAllRestaurants = new GetAllRestaurants(repository);
        getRestaurantById = new GetRestaurantById(repository);
        getRestaurantByLocation = new GetRestaurantByLocation(repository);
        getRestaurantByName = new GetRestaurantByName(repository);

        File file = new File("src/main/resources/data/restaurants.json");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAddAndRetrieveRestaurant() {
        Restaurant restaurant = new Restaurant(
                "1",
                "Sample Restaurant",
                new Location(12.34, 56.78),
                "123 Sample St",
                DishType.ITALIAN,
                4.5,
                "http://example.com/photo.jpg",
                "Great place!"
        );

        boolean added = addRestaurant.execute(restaurant);
        assertTrue(added, "Restaurant should be added successfully");

        // Simulate saving changes
        repository.saveIfDirty();

        Optional<Restaurant> retrievedRestaurant = getRestaurantById.execute("1");
        assertTrue(retrievedRestaurant.isPresent(), "Restaurant should be retrieved by ID");
        assertEquals(restaurant, retrievedRestaurant.get(), "Retrieved restaurant should match the added restaurant");

        Mockito.verify(dataAccess, Mockito.times(1)).saveRestaurants(Mockito.anyList());
    }

    @Test
    public void testRetrieveNonExistentRestaurant() {
        // Try to retrieve a non-existent restaurant
        Optional<Restaurant> retrievedRestaurant = getRestaurantById.execute("999");
        assertFalse(retrievedRestaurant.isPresent(), "Non-existent restaurant should not be found");
    }

    @Test
    public void testUpdateRestaurant() {
        // Create and add a restaurant
        Restaurant restaurant = new Restaurant(
                "2",
                "Another Restaurant",
                new Location(12.34, 56.78),
                "456 Another St",
                DishType.CHINESE,
                4.0,
                "http://example.com/photo2.jpg",
                "Nice food!"
        );
        addRestaurant.execute(restaurant);

        // Update the restaurant details
        Restaurant updatedRestaurant = new Restaurant(
                "2",
                "Updated Restaurant",
                new Location(12.34, 56.78),
                "456 Another St",
                DishType.MEXICAN,
                4.8,
                "http://example.com/photo-updated.jpg",
                "Even better food!"
        );
        repository.update(updatedRestaurant);

        // Simulate saving changes
        repository.saveIfDirty();

        // Verify the update
        Optional<Restaurant> retrievedRestaurant = getRestaurantById.execute("2");
        assertTrue(retrievedRestaurant.isPresent(), "Updated restaurant should be retrieved by ID");
        assertEquals(updatedRestaurant, retrievedRestaurant.get(), "Retrieved restaurant should match the updated restaurant");

        // Verify that the data access layer saved the updated restaurant data
        Mockito.verify(dataAccess, Mockito.times(1)).saveRestaurants(Mockito.anyList());
    }

    @Test
    public void testDeleteRestaurantById() {
        // Create and add a restaurant
        Restaurant restaurant = new Restaurant(
                "3",
                "Delete Restaurant",
                new Location(12.34, 56.78),
                "789 Delete St",
                DishType.INDIAN,
                4.2,
                "http://example.com/photo3.jpg",
                "Good food!"
        );
        addRestaurant.execute(restaurant);

        // Delete the restaurant by ID
        boolean deleted = deleteRestaurantById.execute("3");
        assertTrue(deleted, "Restaurant should be deleted successfully");

        // Simulate saving changes
        repository.saveIfDirty();

        // Verify that the restaurant is no longer in the repository
        Optional<Restaurant> retrievedRestaurant = getRestaurantById.execute("3");
        assertFalse(retrievedRestaurant.isPresent(), "Deleted restaurant should not be found");

        // Verify that the data access layer's save method was called after deletion
        Mockito.verify(dataAccess, Mockito.times(1)).saveRestaurants(Mockito.anyList());
    }

    @Test
    public void testDeleteRestaurantByName() {
        // Create and add a restaurant
        Restaurant restaurant = new Restaurant(
                "4",
                "Delete by Name Restaurant",
                new Location(12.34, 56.78),
                "101 Delete Name St",
                DishType.MEDITERRANEAN,
                4.3,
                "http://example.com/photo4.jpg",
                "Delicious!"
        );
        addRestaurant.execute(restaurant);

        // Delete the restaurant by name
        boolean deleted = deleteRestaurantByName.execute(restaurant);
        assertTrue(deleted, "Restaurant should be deleted by name successfully");

        // Simulate saving changes
        repository.saveIfDirty();

        // Verify that the restaurant is no longer in the repository
        Optional<Restaurant> retrievedRestaurant = getRestaurantByName.execute("Delete by Name Restaurant");
        assertFalse(retrievedRestaurant.isPresent(), "Deleted restaurant by name should not be found");

        // Verify that the data access layer's save method was called after deletion
        Mockito.verify(dataAccess, Mockito.times(1)).saveRestaurants(Mockito.anyList());
    }

    @Test
    public void testGetAllRestaurants() {
        // Add a few restaurants
        Restaurant restaurant1 = new Restaurant(
                "5",
                "Restaurant One",
                new Location(12.34, 56.78),
                "202 All St",
                DishType.JAPANESE,
                4.6,
                "http://example.com/photo5.jpg",
                "Excellent!"
        );
        Restaurant restaurant2 = new Restaurant(
                "6",
                "Restaurant Two",
                new Location(12.34, 56.78),
                "303 All St",
                DishType.THAI,
                4.7,
                "http://example.com/photo6.jpg",
                "Very good!"
        );
        addRestaurant.execute(restaurant1);
        addRestaurant.execute(restaurant2);

        // Simulate saving changes
        repository.saveIfDirty();

        // Retrieve all restaurants
        List<Restaurant> allRestaurants = getAllRestaurants.execute();
        assertEquals(2, allRestaurants.size(), "There should be 2 restaurants");
        assertTrue(allRestaurants.contains(restaurant1), "Restaurant One should be in the list");
        assertTrue(allRestaurants.contains(restaurant2), "Restaurant Two should be in the list");

        // Verify that the data access layer's save method was called
        Mockito.verify(dataAccess, Mockito.times(1)).saveRestaurants(Mockito.anyList());
    }

    @Test
    public void testGetRestaurantByLocation() {
        // Create and add a restaurant
        Restaurant restaurant = new Restaurant(
                "7",
                "Location Restaurant",
                new Location(35.33, 54.32),
                "404 Location St",
                DishType.FRENCH,
                4.9,
                "http://example.com/photo7.jpg",
                "Amazing cuisine!"
        );
        addRestaurant.execute(restaurant);

        // Simulate saving changes
        repository.saveIfDirty();

        // Retrieve the restaurant by location
        Optional<Restaurant> retrievedRestaurant = getRestaurantByLocation.execute(new Location(35.33, 54.32));
        assertTrue(retrievedRestaurant.isPresent(), "Restaurant should be retrieved by location");
        assertEquals(restaurant, retrievedRestaurant.get(), "Retrieved restaurant should match the added restaurant");

        // Verify that the data access layer's save method was called
        Mockito.verify(dataAccess, Mockito.times(1)).saveRestaurants(Mockito.anyList());
    }

    @Test
    public void testGetRestaurantByName() {
        // Create and add a restaurant
        Restaurant restaurant = new Restaurant(
                "8",
                "Name Restaurant",
                new Location(87.65, 43.21),
                "505 Name St",
                DishType.GREEK,
                4.4,
                "http://example.com/photo8.jpg",
                "Unique experience!"
        );
        addRestaurant.execute(restaurant);

        // Simulate saving changes
        repository.saveIfDirty();

        // Retrieve the restaurant by name
        Optional<Restaurant> retrievedRestaurant = getRestaurantByName.execute("Name Restaurant");
        assertTrue(retrievedRestaurant.isPresent(), "Restaurant should be retrieved by name");
        assertEquals(restaurant, retrievedRestaurant.get(), "Retrieved restaurant should match the added restaurant");

        // Verify that the data access layer's save method was called
        Mockito.verify(dataAccess, Mockito.times(1)).saveRestaurants(Mockito.anyList());
    }
}
