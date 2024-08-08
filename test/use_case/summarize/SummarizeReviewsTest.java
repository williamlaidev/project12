package use_case.summarize;

import domain.ReviewSummarizeService;
import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SummarizeReviewsTest {

    private SummarizeReviews summarizeReviews;
    private ReviewSummarizeService reviewSummarizeService;

    @BeforeEach
    public void setUp() {
        reviewSummarizeService = mock(ReviewSummarizeService.class);
        summarizeReviews = new SummarizeReviews(reviewSummarizeService);
    }

    @Test
    public void testExecuteSuccess() throws InterruptedException {
        List<Review> reviews = List.of(
                new Review("restaurant1", "author1", "Great food!", false),
                new Review("restaurant1", "author2", "Good service.", false),
                new Review("restaurant1", "author3", "Nice ambiance.", false),
                new Review("restaurant1", "author4", "Friendly staff.", false)
        );

        Review summarizedReview = new Review("restaurant1", "summarized", "Great food! Good service. Nice ambiance. Friendly staff.", true);
        when(reviewSummarizeService.createSummaryFromReviews(reviews)).thenReturn(summarizedReview);

        Review result = summarizeReviews.execute(reviews);

        assertNotNull(result);
        assertEquals("restaurant1", result.getRestaurantId());
        assertEquals("summarized", result.getAuthor());
        assertEquals("Great food! Good service. Nice ambiance. Friendly staff.", result.getContent());
        assertTrue(result.isSummarized());

        verify(reviewSummarizeService, times(1)).createSummaryFromReviews(reviews);
    }

    @Test
    public void testExecuteInterruptedException() throws InterruptedException {
        List<Review> reviews = List.of(
                new Review("restaurant1", "author1", "Great food!", false),
                new Review("restaurant1", "author2", "Good service.", false),
                new Review("restaurant1", "author3", "Nice ambiance.", false),
                new Review("restaurant1", "author4", "Friendly staff.", false)
        );

        when(reviewSummarizeService.createSummaryFromReviews(reviews)).thenThrow(new InterruptedException("Thread interrupted"));

        Exception exception = assertThrows(InterruptedException.class, () -> {
            summarizeReviews.execute(reviews);
        });

        assertEquals("Thread interrupted", exception.getMessage());

        verify(reviewSummarizeService, times(1)).createSummaryFromReviews(reviews);
    }
}
