package use_case.data.update;

import domain.ReviewRepository;
import entity.operation_result.OperationResult;
import entity.review.Review;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UpdateReviewTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private UpdateReview updateReview;

    public UpdateReviewTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        Review review = new Review("1", "Updated Author", "Updated Content", false);
        OperationResult result = new OperationResult(true, "Update successful");
        when(reviewRepository.update(review)).thenReturn(result);

        OperationResult resultFromUseCase = updateReview.execute(review);
        assertEquals(result.isSuccess(), resultFromUseCase.isSuccess());
        assertEquals(result.getMessage(), resultFromUseCase.getMessage());
    }
}
