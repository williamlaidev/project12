package use_case.search;

import domain.ReviewSearchService;
import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FetchRestaurantReviewsTest {

    private ReviewSearchService reviewSearchService;
    private FetchRestaurantReviews fetchRestaurantReviews;

    @BeforeEach
    public void setUp() {
        reviewSearchService = mock(ReviewSearchService.class);
        fetchRestaurantReviews = new FetchRestaurantReviews(reviewSearchService);
    }

    @Test
    public void testFetchRelevantReviewsSuccess() {
        String restaurantId = "id1";
        int maxReviews = 5;
        List<Review> expectedReviews = List.of(
                new Review("id1", "author1", "content1", false),
                new Review("id2", "author2", "content2", false)
        );

        when(reviewSearchService.fetchRelevantReviews(restaurantId, maxReviews)).thenReturn(expectedReviews);

        List<Review> result = fetchRestaurantReviews.execute(restaurantId, maxReviews);
        assertEquals(expectedReviews, result);
    }

    @Test
    public void testFetchRelevantReviewsEmpty() {
        String restaurantId = "id1";
        int maxReviews = 5;

        when(reviewSearchService.fetchRelevantReviews(restaurantId, maxReviews)).thenReturn(List.of());

        List<Review> result = fetchRestaurantReviews.execute(restaurantId, maxReviews);
        assertEquals(List.of(), result);
    }
}
