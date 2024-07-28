package use_case;

import domain.ReviewRepository;
import entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.data.UpdateReview;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateReviewTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private UpdateReview updateReview;

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
        Review review = new Review("1", "John Doe", "Great food!", false);
        when(reviewRepository.update(review)).thenReturn(true);

        // Act
        boolean result = updateReview.execute(review);

        // Assert
        assertTrue(result);
        verify(reviewRepository, times(1)).update(review);
    }

    @Test
    void execute_whenUpdateFails() {
        // Arrange
        Review review = new Review("1", "John Doe", "Great food!", false);
        when(reviewRepository.update(review)).thenReturn(false);

        // Act
        boolean result = updateReview.execute(review);

        // Assert
        assertFalse(result);
        verify(reviewRepository, times(1)).update(review);
    }
}
