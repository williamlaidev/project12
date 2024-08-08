package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class DeleteAllReviewsByIdTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.deleteAllById("restaurant1")).thenReturn(true);

        DeleteAllReviewsById deleteAllReviewsById = new DeleteAllReviewsById(mockRepository);

        // Act
        boolean result = deleteAllReviewsById.execute("restaurant1");

        // Assert
        assertTrue(result);
        verify(mockRepository, times(1)).deleteAllById("restaurant1");
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.deleteAllById("restaurant1")).thenReturn(false);

        DeleteAllReviewsById deleteAllReviewsById = new DeleteAllReviewsById(mockRepository);

        // Act
        boolean result = deleteAllReviewsById.execute("restaurant1");

        // Assert
        assertFalse(result);
        verify(mockRepository, times(1)).deleteAllById("restaurant1");
    }
}
