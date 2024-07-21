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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateRestaurantTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private UpdateRestaurant updateRestaurant;

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
        // Create a Restaurant instance with all required parameters
        Restaurant restaurant = new Restaurant(
                "1",                  // restaurantId
                "Updated Restaurant", // name
                new Location(1.0, 1.0), // location
                "123 Updated Address", // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.8,                  // averageRating
                "http://example.com/photo-updated.jpg", // photoUrl
                List.of(new Review("1", "author1", "content1", false)), // userReviews
                new Review("1", "author1", "content1", true)            // summarizedReview
        );

        when(repository.update(restaurant)).thenReturn(true);

        boolean result = updateRestaurant.execute(restaurant);

        assertTrue(result);
        verify(repository, times(1)).update(restaurant); // Verify interaction with repository
    }

    @Test
    void executeFailure() {
        // Create a Restaurant instance with all required parameters
        Restaurant restaurant = new Restaurant(
                "1",                  // restaurantId
                "Updated Restaurant", // name
                new Location(1.0, 1.0), // location
                "123 Updated Address", // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.8,                  // averageRating
                "http://example.com/photo-updated.jpg", // photoUrl
                List.of(new Review("1", "author1", "content1", false)), // userReviews
                new Review("1", "author1", "content1", true)            // summarizedReview
        );

        when(repository.update(restaurant)).thenReturn(false);

        boolean result = updateRestaurant.execute(restaurant);

        assertFalse(result);
        verify(repository, times(1)).update(restaurant); // Verify interaction with repository
    }
}
