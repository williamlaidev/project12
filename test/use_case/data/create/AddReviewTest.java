package use_case.data.create;

import domain.ReviewRepository;
import entity.operation_result.OperationResult;
import entity.review.Review;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddReviewTest {

    @Test
    void testExecuteSuccess() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        OperationResult expectedResult = new OperationResult(true, "Success");
        Review review = new Review("restaurant1", "Author Name", "Great place!", false);

        when(mockRepository.add(review)).thenReturn(expectedResult);

        AddReview addReview = new AddReview(mockRepository);

        // Act
        OperationResult result = addReview.execute(review);

        // Assert
        assertEquals(expectedResult, result);
        verify(mockRepository, times(1)).add(review);
    }

    @Test
    void testExecuteFailure() {
        // Arrange
        ReviewRepository mockRepository = mock(ReviewRepository.class);
        OperationResult expectedResult = new OperationResult(false, "Failure");
        Review review = new Review("restaurant1", "Author Name", "Great place!", false);

        when(mockRepository.add(review)).thenReturn(expectedResult);

        AddReview addReview = new AddReview(mockRepository);

        // Act
        OperationResult result = addReview.execute(review);

        // Assert
        assertEquals(expectedResult, result);
        verify(mockRepository, times(1)).add(review);
    }
}
