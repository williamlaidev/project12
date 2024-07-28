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
import use_case.data.GetAllRestaurants;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAllRestaurantsTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private GetAllRestaurants getAllRestaurants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @AfterEach
    void tearDown() {
        // Mockito.reset(repository); // Optionally reset mocks if needed
    }

    @Test
    void execute() {
        // Create a list of Restaurant instances to return
        List<Restaurant> expectedRestaurants = Arrays.asList(
                new Restaurant(
                        "1",                  // restaurantId
                        "Restaurant One",     // name
                        new Location(1.0, 1.0), // location
                        "123 Address One",    // address
                        DishType.PIZZA,       // dishType (use appropriate enum value)
                        4.0,                  // averageRating
                        "http://example.com/photo1.jpg", // photoUrl
                        List.of(new Review("1", "author1", "content1", false)), // userReviews
                        new Review("1", "author1", "content1", true)            // summarizedReview
                ),
                new Restaurant(
                        "2",                  // restaurantId
                        "Restaurant Two",     // name
                        new Location(2.0, 2.0), // location
                        "456 Address Two",    // address
                        DishType.RAMEN,      // dishType (use appropriate enum value)
                        3.5,                  // averageRating
                        "http://example.com/photo2.jpg", // photoUrl
                        List.of(new Review("2", "author1", "content1", false)), // userReviews
                        new Review("2", "author1", "content1", true)            // summarizedReview
                )
        );

        // Mock the repository's getAll method to return the list of restaurants
        when(repository.getAll()).thenReturn(expectedRestaurants);

        // Execute the getAll operation
        List<Restaurant> result = getAllRestaurants.execute();

        // Verify the result and interaction with the repository
        assertEquals(expectedRestaurants, result);
        verify(repository, times(1)).getAll(); // Verify interaction with repository
    }
}
