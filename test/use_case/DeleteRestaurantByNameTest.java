package use_case;

import domain.RestaurantRepository;
import entity.DishType;
import entity.Location;
import entity.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteRestaurantByNameTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private DeleteRestaurantByName deleteRestaurantByName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @AfterEach
    void tearDown() {
        // Mockito.reset(repository); // Optionally reset mocks if needed
    }

    @Test
    void executeSuccess() {
        // Create a Restaurant instance with required parameters
        Restaurant restaurant = new Restaurant(
                "1",                  // restaurantId
                "Test Restaurant",    // name
                new Location(1.0, 1.0), // location
                "123 Test Address",   // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.5,                  // averageRating
                "http://example.com/photo.jpg", // photoUrl
                "Great place!"        // summarizedReview
        );

        // Mock the repository's deleteByName method to return true for successful deletion
        when(repository.deleteByName(restaurant)).thenReturn(true);

        // Execute the delete operation
        boolean result = deleteRestaurantByName.execute(restaurant);

        // Verify the result and interaction with the repository
        assertTrue(result);
        verify(repository, times(1)).deleteByName(restaurant); // Verify interaction with repository
    }

    @Test
    void executeFailure() {
        // Create a Restaurant instance with required parameters
        Restaurant restaurant = new Restaurant(
                "1",                  // restaurantId
                "Test Restaurant",    // name
                new Location(1.0, 1.0), // location
                "123 Test Address",   // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.5,                  // averageRating
                "http://example.com/photo.jpg", // photoUrl
                "Great place!"        // summarizedReview
        );

        // Mock the repository's deleteByName method to return false for unsuccessful deletion
        when(repository.deleteByName(restaurant)).thenReturn(false);

        // Execute the delete operation
        boolean result = deleteRestaurantByName.execute(restaurant);

        // Verify the result and interaction with the repository
        assertFalse(result);
        verify(repository, times(1)).deleteByName(restaurant); // Verify interaction with repository
    }
}
