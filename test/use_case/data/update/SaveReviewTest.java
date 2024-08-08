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

public class SaveReviewTest {

    private ReviewRepository reviewRepository;
    private SaveReview saveReview;
    private OperationResultSuccessFactory successFactory;

    @BeforeEach
    public void setUp() {
        reviewRepository = mock(ReviewRepository.class);
        saveReview = new SaveReview(reviewRepository);
        successFactory = new OperationResultSuccessFactory();
    }

    @Test
    public void testExecute() {
        Review review = new Review("id1", "Author1", "Content1", false);
        OperationResult expected = successFactory.create("Review saved successfully");

        when(reviewRepository.save(review)).thenReturn(expected);

        OperationResult result = saveReview.execute(review);

        assertEquals(expected, result);
    }
}
