package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindSummarizedReviewTest {

    private ReviewRepository reviewRepository;
    private FindSummarizedReview findSummarizedReview;

    @BeforeEach
    public void setUp() {
        reviewRepository = mock(ReviewRepository.class);
        findSummarizedReview = new FindSummarizedReview(reviewRepository);
    }

    @Test
    public void testExecute() {
        String restaurantId = "id1";
        Review expectedReview = new Review(restaurantId, "Author1", "Summarized content", true);

        when(reviewRepository.findSummarizedReview(restaurantId)).thenReturn(Optional.of(expectedReview));

        Optional<Review> result = findSummarizedReview.execute(restaurantId);

        assertTrue(result.isPresent());
        assertEquals(expectedReview, result.get());
    }

    @Test
    public void testExecuteNotFound() {
        String restaurantId = "id1";

        when(reviewRepository.findSummarizedReview(restaurantId)).thenReturn(Optional.empty());

        Optional<Review> result = findSummarizedReview.execute(restaurantId);

        assertTrue(result.isEmpty());
    }
}
