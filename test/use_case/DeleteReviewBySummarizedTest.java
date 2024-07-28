package use_case;

import domain.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.data.DeleteReviewBySummarized;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteReviewBySummarizedTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private DeleteReviewBySummarized deleteReviewBySummarized;

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
        boolean summarized = true;
        when(reviewRepository.deleteReviewBySummarized(summarized)).thenReturn(true);

        // Act
        boolean result = deleteReviewBySummarized.execute(summarized);

        // Assert
        assertTrue(result);
        verify(reviewRepository, times(1)).deleteReviewBySummarized(summarized);
    }

    @Test
    void execute_whenDeletionFails() {
        // Arrange
        boolean summarized = true;
        when(reviewRepository.deleteReviewBySummarized(summarized)).thenReturn(false);

        // Act
        boolean result = deleteReviewBySummarized.execute(summarized);

        // Assert
        assertFalse(result);
        verify(reviewRepository, times(1)).deleteReviewBySummarized(summarized);
    }
}
