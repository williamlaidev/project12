package use_case.data.delete;

import domain.ReviewRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class DeleteUserReviewsByIdTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.deleteUserById("restaurant1")).thenReturn(true);

        DeleteUserReviewsById deleteUserReviewsById = new DeleteUserReviewsById(mockRepository);

        // Act
        boolean result = deleteUserReviewsById.execute("restaurant1");

        // Assert
        assertTrue(result);
        verify(mockRepository, times(1)).deleteUserById("restaurant1");
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        when(mockRepository.deleteUserById("restaurant1")).thenReturn(false);

        DeleteUserReviewsById deleteUserReviewsById = new DeleteUserReviewsById(mockRepository);

        // Act
        boolean result = deleteUserReviewsById.execute("restaurant1");

        // Assert
        assertFalse(result);
        verify(mockRepository, times(1)).deleteUserById("restaurant1");
    }
}
