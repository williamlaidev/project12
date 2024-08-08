package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindUserReviewsTest {

    private ReviewRepository reviewRepository;
    private FindUserReviews findUserReviews;

    @BeforeEach
    public void setUp() {
        reviewRepository = mock(ReviewRepository.class);
        findUserReviews = new FindUserReviews(reviewRepository);
    }

    @Test
    public void testExecute() {
        String restaurantId = "id1";
        List<Review> expectedReviews = List.of(
                new Review(restaurantId, "Author1", "Content1", false),
                new Review(restaurantId, "Author2", "Content2", false)
        );

        when(reviewRepository.findUserReviews(restaurantId)).thenReturn(expectedReviews);

        List<Review> result = findUserReviews.execute(restaurantId);

        assertEquals(expectedReviews, result);
    }

    @Test
    public void testExecuteNoReviews() {
        String restaurantId = "id1";
        List<Review> expectedReviews = List.of();

        when(reviewRepository.findUserReviews(restaurantId)).thenReturn(expectedReviews);

        List<Review> result = findUserReviews.execute(restaurantId);

        assertEquals(expectedReviews, result);
    }
}
