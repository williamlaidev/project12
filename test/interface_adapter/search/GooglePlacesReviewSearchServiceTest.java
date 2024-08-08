package interface_adapter.search;

import entity.review.Review;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.data.create.AddReview;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GooglePlacesReviewSearchServiceTest {

    private GooglePlacesReviewSearchService reviewSearchService;
    private GooglePlacesReviewSearchAdapter mockAdapter;
    private ReviewSearchGateways mockGateways;
    private AddReview mockAddReviewUseCase;

    @BeforeEach
    public void setUp() {
        mockAdapter = mock(GooglePlacesReviewSearchAdapter.class);
        mockGateways = mock(ReviewSearchGateways.class);
        mockAddReviewUseCase = mock(AddReview.class);
        reviewSearchService = new GooglePlacesReviewSearchService(mockAdapter, mockGateways, mockAddReviewUseCase);
    }

    @Test
    public void testFetchRelevantReviewsSuccess() throws Exception {
        String restaurantId = "restaurant123";
        int maxReviews = 3;

        JSONObject reviewJson1 = new JSONObject()
                .put("author_name", "Kera Kim")
                .put("text", "Great food and drinks!");

        JSONObject reviewJson2 = new JSONObject()
                .put("author_name", "Jenny Lee")
                .put("text", "Amazing experience!");

        Review review1 = new Review(restaurantId, "Kera Kim", "Great food and drinks!", false);
        Review review2 = new Review(restaurantId, "Jenny Lee", "Amazing experience!", false);

        when(mockGateways.fetchRelevantReviews(restaurantId)).thenReturn(List.of(reviewJson1, reviewJson2));
        when(mockAdapter.adaptToReview(reviewJson1, restaurantId)).thenReturn(review1);
        when(mockAdapter.adaptToReview(reviewJson2, restaurantId)).thenReturn(review2);

        List<Review> reviews = reviewSearchService.fetchRelevantReviews(restaurantId, maxReviews);

        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        verify(mockAddReviewUseCase).execute(review1);
        verify(mockAddReviewUseCase).execute(review2);
    }

    @Test
    public void testFetchRelevantReviewsException() throws Exception {
        String restaurantId = "restaurant123";
        int maxReviews = 3;

        when(mockGateways.fetchRelevantReviews(restaurantId)).thenThrow(new RuntimeException("Fetch error"));

        List<Review> reviews = reviewSearchService.fetchRelevantReviews(restaurantId, maxReviews);

        assertNotNull(reviews);
        assertTrue(reviews.isEmpty());
        verify(mockAddReviewUseCase, never()).execute(any());
    }

    @Test
    public void testFetchRelevantReviewsLimitExceeded() throws Exception {
        String restaurantId = "restaurant123";
        int maxReviews = 1;

        JSONObject reviewJson1 = new JSONObject()
                .put("author_name", "Kera Kim")
                .put("text", "Great food and drinks!");

        JSONObject reviewJson2 = new JSONObject()
                .put("author_name", "Jenny Lee")
                .put("text", "Amazing experience!");

        Review review1 = new Review(restaurantId, "Kera Kim", "Great food and drinks!", false);

        when(mockGateways.fetchRelevantReviews(restaurantId)).thenReturn(List.of(reviewJson1, reviewJson2));
        when(mockAdapter.adaptToReview(reviewJson1, restaurantId)).thenReturn(review1);

        List<Review> reviews = reviewSearchService.fetchRelevantReviews(restaurantId, maxReviews);

        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals(review1, reviews.get(0));
        verify(mockAddReviewUseCase).execute(review1);
    }

    @Test
    public void testFetchRelevantReviewsNoReviews() throws Exception {
        String restaurantId = "restaurant123";
        int maxReviews = 3;

        when(mockGateways.fetchRelevantReviews(restaurantId)).thenReturn(List.of());

        List<Review> reviews = reviewSearchService.fetchRelevantReviews(restaurantId, maxReviews);

        assertNotNull(reviews);
        assertTrue(reviews.isEmpty());
        verify(mockAddReviewUseCase, never()).execute(any());
    }
}
