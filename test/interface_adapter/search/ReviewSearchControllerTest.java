package interface_adapter.search;

import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search.FetchRestaurantReviews;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewSearchControllerTest {

    private ReviewSearchController reviewSearchController;
    private FetchRestaurantReviews fetchRestaurantReviewsMock;

    @BeforeEach
    public void setUp() {
        fetchRestaurantReviewsMock = mock(FetchRestaurantReviews.class);
        reviewSearchController = new ReviewSearchController(fetchRestaurantReviewsMock);
    }

    @Test
    public void testGetReviews() {
        String restaurantId = "ChIJN1t_tDeuEmsRUsoyG83frY4";
        int maxReviews = 5;
        List<Review> expectedReviews = Arrays.asList(
                new Review(restaurantId, "Author1", "Great food!", false),
                new Review(restaurantId, "Author2", "Nice place.", false)
        );

        when(fetchRestaurantReviewsMock.execute(restaurantId, maxReviews)).thenReturn(expectedReviews);

        List<Review> actualReviews = reviewSearchController.getReviews(restaurantId, maxReviews);

        assertNotNull(actualReviews);
        assertEquals(2, actualReviews.size());
        assertEquals(expectedReviews, actualReviews);

        verify(fetchRestaurantReviewsMock, times(1)).execute(restaurantId, maxReviews);
    }

    @Test
    public void testGetReviewsNoReviews() {
        String restaurantId = "ChIJN1t_tDeuEmsRUsoyG83frY4";
        int maxReviews = 5;

        when(fetchRestaurantReviewsMock.execute(restaurantId, maxReviews)).thenReturn(Arrays.asList());

        List<Review> actualReviews = reviewSearchController.getReviews(restaurantId, maxReviews);

        assertNotNull(actualReviews);
        assertTrue(actualReviews.isEmpty());

        verify(fetchRestaurantReviewsMock, times(1)).execute(restaurantId, maxReviews);
    }

    @Test
    public void testGetReviewsWithNullRestaurantId() {
        int maxReviews = 5;

        when(fetchRestaurantReviewsMock.execute(null, maxReviews)).thenThrow(new IllegalArgumentException("Restaurant ID cannot be null"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewSearchController.getReviews(null, maxReviews);
        });

        assertEquals("Restaurant ID cannot be null", exception.getMessage());

        verify(fetchRestaurantReviewsMock, times(1)).execute(null, maxReviews);
    }
}
