package use_case.search;

import domain.ReviewSearchService;
import entity.review.Review;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FetchRestaurantReviewsTest {

    @Mock
    private ReviewSearchService reviewSearchService;

    @InjectMocks
    private FetchRestaurantReviews fetchRestaurantReviews;

    public FetchRestaurantReviewsTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        String restaurantId = "123";
        int maxReviews = 5;
        Review review1 = new Review(restaurantId, "Author1", "Content1", false);
        Review review2 = new Review(restaurantId, "Author2", "Content2", true);
        List<Review> expectedReviews = Arrays.asList(review1, review2);

        when(reviewSearchService.fetchRelevantReviews(restaurantId, maxReviews)).thenReturn(expectedReviews);

        List<Review> actualReviews = fetchRestaurantReviews.execute(restaurantId, maxReviews);

        assertEquals(expectedReviews, actualReviews);
    }
}
