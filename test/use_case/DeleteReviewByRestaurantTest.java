package use_case;

import domain.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.data.DeleteReviewByRestaurant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteReviewByRestaurantTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private DeleteReviewByRestaurant deleteReviewByRestaurant;

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
        when(reviewRepository.deleteReviewByRestaurant(restaurantId)).thenReturn(true);

        // Act
        boolean result = deleteReviewByRestaurant.execute(restaurantId);

        // Assert
        assertTrue(result);
        verify(reviewRepository, times(1)).deleteReviewByRestaurant(restaurantId);
    }

    @Test
    void execute_whenDeletionFails() {
        // Arrange
        String restaurantId = "1";
        when(reviewRepository.deleteReviewByRestaurant(restaurantId)).thenReturn(false);

        // Act
        boolean result = deleteReviewByRestaurant.execute(restaurantId);

        // Assert
        assertFalse(result);
        verify(reviewRepository, times(1)).deleteReviewByRestaurant(restaurantId);
    }
}
