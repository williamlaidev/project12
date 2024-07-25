package use_case;

import domain.ReviewRepository;
import entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.Data.GetReviewByRestaurant;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetReviewByRestaurantTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private GetReviewByRestaurant getReviewByRestaurant;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void execute() {
        // Arrange
        String restaurantId = "1";
        Review review = new Review(restaurantId, "John Doe", "Great food!", false);
        when(reviewRepository.getReviewByRestaurant(restaurantId)).thenReturn(Optional.of(review));

        // Act
        Optional<Review> result = getReviewByRestaurant.execute(restaurantId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(review, result.get());
        verify(reviewRepository, times(1)).getReviewByRestaurant(restaurantId);
    }

    @Test
    void execute_whenNoReviewFound() {
        // Arrange
        String restaurantId = "1";
        when(reviewRepository.getReviewByRestaurant(restaurantId)).thenReturn(Optional.empty());

        // Act
        Optional<Review> result = getReviewByRestaurant.execute(restaurantId);

        // Assert
        assertFalse(result.isPresent());
        verify(reviewRepository, times(1)).getReviewByRestaurant(restaurantId);
    }
}
