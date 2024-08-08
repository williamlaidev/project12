package use_case.data.read;

import domain.ReviewRepository;
import entity.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllReviewsTest {

    private ReviewRepository reviewRepository;
    private FindAllReviews findAllReviews;

    @BeforeEach
    public void setUp() {
        reviewRepository = mock(ReviewRepository.class);
        findAllReviews = new FindAllReviews(reviewRepository);
    }

    @Test
    public void testExecute() {
        List<Review> expectedReviews = List.of(
                new Review("restaurantId1", "Author1", "Content1", false),
                new Review("restaurantId2", "Author2", "Content2", true)
        );

        when(reviewRepository.findAll()).thenReturn(expectedReviews);

        List<Review> result = findAllReviews.execute();

        assertEquals(expectedReviews, result);
    }
}
