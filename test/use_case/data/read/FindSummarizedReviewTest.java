package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class FindSummarizedReviewTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private FindSummarizedReview findSummarizedReview;

    public FindSummarizedReviewTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteFound() {
        Review review = new Review("1", "Author", "Summarized content", true);
        when(reviewRepository.findSummarizedReview("1")).thenReturn(Optional.of(review));

        Optional<Review> result = findSummarizedReview.execute("1");
        assertTrue(result.isPresent());
        assertEquals("1", result.get().getRestaurantId());
        assertTrue(result.get().isSummarized());
    }

    @Test
    public void testExecuteNotFound() {
        when(reviewRepository.findSummarizedReview("999")).thenReturn(Optional.empty());

        Optional<Review> result = findSummarizedReview.execute("999");
        assertTrue(result.isEmpty());
    }
}
