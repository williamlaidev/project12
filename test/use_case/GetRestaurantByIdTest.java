package use_case;

import domain.RestaurantRepository;
import entity.DishType;
import entity.Location;
import entity.Restaurant;
import entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetRestaurantByIdTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private GetRestaurantById getRestaurantById;

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
        // Create a Restaurant instance
        Restaurant restaurant = new Restaurant(
                "1",                  // restaurantId
                "Restaurant One",     // name
                new Location(1.0, 1.0), // location
                "123 Address One",    // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.0,                  // averageRating
                "http://example.com/photo1.jpg", // photoUrl
                List.of(new Review("1", "author1", "content1", false)), // userReviews
                new Review("1", "author1", "content1", true)            // summarizedReview
        );

        // Prepare the ID to fetch
        String restaurantId = "1";

        // Mock the repository's getById method to return the restaurant
        when(repository.getById(restaurantId)).thenReturn(Optional.of(restaurant));

        // Execute the getById operation
        Optional<Restaurant> result = getRestaurantById.execute(restaurantId);

        // Verify the result and interaction with the repository
        assertTrue(result.isPresent());
        assertEquals(restaurant, result.get());
        verify(repository, times(1)).getById(restaurantId); // Verify interaction with repository
    }

    @Test
    void executeNotFound() {
        // Prepare the ID to fetch
        String restaurantId = "1";

        // Mock the repository's getById method to return an empty Optional
        when(repository.getById(restaurantId)).thenReturn(Optional.empty());

        // Execute the getById operation
        Optional<Restaurant> result = getRestaurantById.execute(restaurantId);

        // Verify the result and interaction with the repository
        assertFalse(result.isPresent());
        verify(repository, times(1)).getById(restaurantId); // Verify interaction with repository
    }
}
