package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindUserReviewsTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private FindUserReviews findUserReviews;

    public FindUserReviewsTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        Review review1 = new Review("1", "Author 1", "Content 1", false);
        Review review2 = new Review("1", "Author 2", "Content 2", false);

        when(reviewRepository.findUserReviews("1")).thenReturn(Arrays.asList(review1, review2));

        List<Review> result = findUserReviews.execute("1");
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getRestaurantId());
        assertEquals("1", result.get(1).getRestaurantId());
    }
}
