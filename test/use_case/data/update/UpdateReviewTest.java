package use_case.data.update;

import entity.operation_result.OperationResult;
import entity.operation_result.OperationResultSuccessFactory;
import entity.review.Review;
import domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateReviewTest {

    private ReviewRepository reviewRepository;
    private UpdateReview updateReview;
    private OperationResultSuccessFactory successFactory;

    @BeforeEach
    public void setUp() {
        reviewRepository = mock(ReviewRepository.class);
        updateReview = new UpdateReview(reviewRepository);
        successFactory = new OperationResultSuccessFactory();
    }

    @Test
    public void testExecute() {
        Review review = new Review("id1", "Author1", "Updated Content", false);
        OperationResult expected = successFactory.create("Review updated successfully");

        when(reviewRepository.update(review)).thenReturn(expected);

        OperationResult result = updateReview.execute(review);

        assertEquals(expected, result);
    }
}
