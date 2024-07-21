package interface_adapter;

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
import use_case.GetRestaurantById;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewMapperImplTest {

    @Mock
    private GetRestaurantById getRestaurantById;

    @InjectMocks
    private ReviewMapperImpl reviewMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // Clean up resources if needed
    }

    @Test
    void testMapReviewsToString() {
        // Arrange
        Review review1 = new Review("1", "Author1", "Great food!", false);
        Review review2 = new Review("1", "Author2", "Nice ambiance.", false);
        Review review3 = new Review("2", "Author3", "Not bad.", false);

        Restaurant restaurant1 = new Restaurant(
                "1", "Restaurant1", new Location(40.7128, -74.0060), "123 Main St",
                DishType.AMERICAN, 4.5, "http://example.com/photo1", Arrays.asList(review1, review2), null
        );
        Restaurant restaurant2 = new Restaurant(
                "2", "Restaurant2", new Location(34.0522, -118.2437), "456 Elm St",
                DishType.ITALIAN, 4.0, "http://example.com/photo2", List.of(review3), null
        );

        when(getRestaurantById.execute("1")).thenReturn(Optional.of(restaurant1));
        when(getRestaurantById.execute("2")).thenReturn(Optional.of(restaurant2));

        // Act
        String result = reviewMapper.mapReviewsToString(Arrays.asList(review1, review2, review3));

        // Assert
        assertEquals("Restaurant1: Great food! Nice ambiance.; Restaurant2: Not bad.", result);
    }

    @Test
    void testMapReviewsToStringWithUnknownRestaurant() {
        // Arrange
        Review review1 = new Review("1", "Author1", "Great food!", false);
        Review review2 = new Review("2", "Author2", "Nice ambiance.", false);

        // Mocking the behavior of getRestaurantById
        when(getRestaurantById.execute("1")).thenReturn(Optional.empty());
        when(getRestaurantById.execute("2")).thenReturn(Optional.of(
                new Restaurant("2", "Restaurant2", new Location(34.0522, -118.2437), "456 Elm St",
                        DishType.ITALIAN, 4.0, "http://example.com/photo2", List.of(review2), null)
        ));

        // Act
        String result = reviewMapper.mapReviewsToString(Arrays.asList(review1, review2));

        // Assert
        assertEquals("Unknown Restaurant: Great food!; Restaurant2: Nice ambiance.", result);
    }

    @Test
    void testMapReviewsToStringWithEmptyList() {
        // Act
        String result = reviewMapper.mapReviewsToString(List.of());

        // Assert
        assertEquals("No Reviews", result);
    }
}
