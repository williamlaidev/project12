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

public class FindAllReviewsTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private FindAllReviews findAllReviews;

    public FindAllReviewsTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        Review review1 = new Review("1", "Author 1", "Content 1", true);
        Review review2 = new Review("2", "Author 2", "Content 2", false);

        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review1, review2));

        List<Review> result = findAllReviews.execute();
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getRestaurantId());
        assertEquals("2", result.get(1).getRestaurantId());
    }
}
