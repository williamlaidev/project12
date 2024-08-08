package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class DeleteSummarizedReviewByIdTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.deleteSummarizedById("restaurant1")).thenReturn(true);

        DeleteSummarizedReviewById deleteSummarizedReviewById = new DeleteSummarizedReviewById(mockRepository);

        // Act
        boolean result = deleteSummarizedReviewById.execute("restaurant1");

        // Assert
        assertTrue(result);
        verify(mockRepository, times(1)).deleteSummarizedById("restaurant1");
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.deleteSummarizedById("restaurant1")).thenReturn(false);

        DeleteSummarizedReviewById deleteSummarizedReviewById = new DeleteSummarizedReviewById(mockRepository);

        // Act
        boolean result = deleteSummarizedReviewById.execute("restaurant1");

        // Assert
        assertFalse(result);
        verify(mockRepository, times(1)).deleteSummarizedById("restaurant1");
    }
}
