package interface_adapter.summarize;

import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.data.create.AddReview;
import use_case.data.read.FindSummarizedReview;
import use_case.data.update.UpdateReview;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GeminiReviewSummarizeServiceTest {

    private ReviewSummarizeGateways reviewSummarizeGateways;
    private ReviewSummarizeAdapter reviewSummarizeAdapter;
    private AddReview addReviewUseCase;
    private UpdateReview updateReviewUseCase;
    private FindSummarizedReview findSummarizedReviewUseCase;
    private GeminiReviewSummarizeService service;

    @BeforeEach
    public void setUp() {
        reviewSummarizeGateways = mock(ReviewSummarizeGateways.class);
        reviewSummarizeAdapter = mock(ReviewSummarizeAdapter.class);
        addReviewUseCase = mock(AddReview.class);
        updateReviewUseCase = mock(UpdateReview.class);
        findSummarizedReviewUseCase = mock(FindSummarizedReview.class);
        service = new GeminiReviewSummarizeService(reviewSummarizeGateways, reviewSummarizeAdapter, addReviewUseCase, updateReviewUseCase, findSummarizedReviewUseCase);
    }

    @Test
    public void testCreateSummaryFromReviews() throws InterruptedException {
        List<Review> reviews = List.of(
                new Review("temporary_id_1", "Kera Kim", "Great food!", false),
                new Review("temporary_id_1", "Jenny Lee", "Lovely ambiance.", false)
        );

        String reviewContent = "Restaurant 1:\n- Great food!\n- Lovely ambiance.";
        String summarizedContent = "Great food and lovely ambiance at Restaurant 1.";

        when(reviewSummarizeAdapter.adaptToReviewString(reviews)).thenReturn(reviewContent);
        when(reviewSummarizeGateways.summarizeReviews(reviewContent)).thenReturn(summarizedContent);
        when(reviewSummarizeAdapter.adaptToReview("temporary_id_1", summarizedContent)).thenReturn(new Review("temporary_id_1", "Gemini", summarizedContent, true));
        when(findSummarizedReviewUseCase.execute("temporary_id_1")).thenReturn(Optional.empty());

        Review summarizedReview = service.createSummaryFromReviews(reviews);

        assertEquals("temporary_id_1", summarizedReview.getRestaurantId());
        assertEquals("Gemini", summarizedReview.getAuthor());
        assertEquals(summarizedContent, summarizedReview.getContent());
        assertTrue(summarizedReview.isSummarized());

        verify(addReviewUseCase, times(1)).execute(any(Review.class));
        verify(updateReviewUseCase, never()).execute(any(Review.class));
    }

    @Test
    public void testCreateSummaryFromReviewsWhenSummarizedReviewExists() throws InterruptedException {
        List<Review> reviews = List.of(
                new Review("temporary_id_1", "Kera Kim", "Great food!", false),
                new Review("temporary_id_1", "Jenny Lee", "Lovely ambiance.", false)
        );

        String reviewContent = "Restaurant 1:\n- Great food!\n- Lovely ambiance.";
        String summarizedContent = "Great food and lovely ambiance at Restaurant 1.";

        when(reviewSummarizeAdapter.adaptToReviewString(reviews)).thenReturn(reviewContent);
        when(reviewSummarizeGateways.summarizeReviews(reviewContent)).thenReturn(summarizedContent);
        when(reviewSummarizeAdapter.adaptToReview("temporary_id_1", summarizedContent)).thenReturn(new Review("temporary_id_1", "Gemini", summarizedContent, true));
        when(findSummarizedReviewUseCase.execute("temporary_id_1")).thenReturn(Optional.of(new Review("temporary_id_1", "Gemini", "Old summary", true)));

        Review summarizedReview = service.createSummaryFromReviews(reviews);

        assertEquals("temporary_id_1", summarizedReview.getRestaurantId());
        assertEquals("Gemini", summarizedReview.getAuthor());
        assertEquals(summarizedContent, summarizedReview.getContent());
        assertTrue(summarizedReview.isSummarized());

        verify(updateReviewUseCase, times(1)).execute(any(Review.class));
        verify(addReviewUseCase, never()).execute(any(Review.class));
    }

    @Test
    public void testCreateSummaryFromReviewsWithException() throws InterruptedException {
        List<Review> reviews = List.of(
                new Review("temporary_id_1", "Kera Kim", "Great food!", false),
                new Review("temporary_id_1", "Jenny Lee", "Lovely ambiance.", false)
        );

        String reviewContent = "Restaurant 1:\n- Great food!\n- Lovely ambiance.";
        String summarizedContent = "Great food and lovely ambiance at Restaurant 1.";

        when(reviewSummarizeAdapter.adaptToReviewString(reviews)).thenReturn(reviewContent);
        when(reviewSummarizeGateways.summarizeReviews(reviewContent)).thenThrow(new RuntimeException("Error summarizing reviews"));

        try {
            service.createSummaryFromReviews(reviews);
        } catch (RuntimeException e) {
            assertEquals("Error summarizing reviews", e.getMessage());
        }

        verify(addReviewUseCase, never()).execute(any(Review.class));
        verify(updateReviewUseCase, never()).execute(any(Review.class));
    }
}
