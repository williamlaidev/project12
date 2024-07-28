package use_case;

import domain.ReviewSummarizeService;
import entity.Review;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.summarize.SummarizeReviews;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SummarizeReviewsTest {

    private ReviewSummarizeService mockReviewSummarizeService;
    private SummarizeReviews summarizeReviews;

    @BeforeEach
    public void setUp() {
        // Create a mock of ReviewSummarizeService
        mockReviewSummarizeService = Mockito.mock(ReviewSummarizeService.class);
        // Initialize the use case with the mock service
        summarizeReviews = new SummarizeReviews(mockReviewSummarizeService);
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources, if any
        mockReviewSummarizeService = null;
        summarizeReviews = null;
    }

    @Test
    public void testSummarizeReviews() throws InterruptedException {
        // Create mock reviews with longer content
        Review review1 = new Review("1", "Author1", "This is a very long review content. It goes into great detail about the restaurant, describing the ambiance, service, and food quality. The reviewer provides an extensive overview, including the positives and negatives, highlighting their overall experience in a comprehensive manner.", true);
        Review review2 = new Review("1", "Author2", "Another long review content that delves deeply into the various aspects of the restaurant. The review covers different dishes, the atmosphere of the place, and the level of service received. It is thorough and provides a well-rounded perspective on the dining experience.", true);

        // Mock the behavior of the summarizeService
        when(mockReviewSummarizeService.summarize(Arrays.asList(review1, review2))).thenReturn(
                new Review("1", "Gemini API", "Summarized content should be present", true)
        );

        // Execute the use case
        Review summarizedReview = summarizeReviews.execute(Arrays.asList(review1, review2));

        // Verify that the summarized review content is not null
        assertNotNull(summarizedReview.getContent(), "The summarized review content should not be null");
    }
}
