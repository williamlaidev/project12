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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetRestaurantByLocationTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private GetRestaurantByLocation getRestaurantByLocation;

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
        // Create a Location instance
        Location location = new Location(1.0, 1.0);

        // Create a Restaurant instance
        Restaurant restaurant = new Restaurant(
                "1",                  // restaurantId
                "Restaurant One",     // name
                location,              // location
                "123 Address One",    // address
                DishType.PIZZA,       // dishType (use appropriate enum value)
                4.0,                  // averageRating
                "http://example.com/photo1.jpg", // photoUrl
                "Great food!"         // summarizedReview
        );

        // Mock the repository's getByLocation method to return the restaurant
        when(repository.getByLocation(location)).thenReturn(Optional.of(restaurant));

        // Execute the getByLocation operation
        Optional<Restaurant> result = getRestaurantByLocation.execute(location);

        // Verify the result and interaction with the repository
        assertTrue(result.isPresent());
        assertEquals(restaurant, result.get());
        verify(repository, times(1)).getByLocation(location); // Verify interaction with repository
    }

    @Test
    void executeNotFound() {
        // Create a Location instance
        Location location = new Location(1.0, 1.0);

        // Mock the repository's getByLocation method to return an empty Optional
        when(repository.getByLocation(location)).thenReturn(Optional.empty());

        // Execute the getByLocation operation
        Optional<Restaurant> result = getRestaurantByLocation.execute(location);

        // Verify the result and interaction with the repository
        assertFalse(result.isPresent());
        verify(repository, times(1)).getByLocation(location); // Verify interaction with repository
    }
}
