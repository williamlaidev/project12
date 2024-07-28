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
import use_case.data.AddRestaurant;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddRestaurantTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private AddRestaurant addRestaurant;

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
                "Test Restaurant",    // name
                new Location(1.0, 1.0), // location
                "123 Test Address",   // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.5,                  // averageRating
                "http://example.com/photo.jpg", // photoUrl
                List.of(new Review("1", "author1", "content1", false)), // userReviews
                new Review("1", "author1", "content1", true)            // summarizedReview
        );

        when(repository.add(restaurant)).thenReturn(true);

        boolean result = addRestaurant.execute(restaurant);

        assertTrue(result);
        verify(repository, times(1)).add(restaurant); // Verify interaction with repository
    }

    @Test
    void executeFailure() {
        // Create a Restaurant instance with all required parameters
        Restaurant restaurant = new Restaurant(
                "1",                  // restaurantId
                "Test Restaurant",    // name
                new Location(1.0, 1.0), // location
                "123 Test Address",   // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.5,                  // averageRating
                "http://example.com/photo.jpg", // photoUrl
                List.of(new Review("1", "author1", "content1", false)), // userReviews
                new Review("1", "author1", "content1", true)            // summarizedReview
        );

        when(repository.add(restaurant)).thenReturn(false);

        boolean result = addRestaurant.execute(restaurant);

        assertFalse(result);
        verify(repository, times(1)).add(restaurant); // Verify interaction with repository
    }
}
