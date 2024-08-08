package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class ClearAllReviewsTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.clearAll()).thenReturn(true);

        ClearAllReviews clearAllReviews = new ClearAllReviews(mockRepository);

        // Act
        boolean result = clearAllReviews.execute();

        // Assert
        assertTrue(result);
        verify(mockRepository, times(1)).clearAll();
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.clearAll()).thenReturn(false);

        ClearAllReviews clearAllReviews = new ClearAllReviews(mockRepository);

        // Act
        boolean result = clearAllReviews.execute();

        // Assert
        assertFalse(result);
        verify(mockRepository, times(1)).clearAll();
    }
}
