package integration;

import domain.ReviewSummarizeService;
import entity.Review;
import entity.Restaurant;
import entity.Location;
import entity.DishType;
import use_case.Summarize.SummarizeReviews;
import use_case.Data.GetRestaurantById;
import framework.Summarize.GeminiReviewSummarizeService;
import interface_adapter.Summarize.ReviewSummarizeControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewSummarizeServiceIntegrationTest {

    private ReviewSummarizeControllerImpl reviewSummarizeController;
    private List<Review> reviews;

    @BeforeEach
    public void setUp() {
        GetRestaurantById getRestaurantByIdMock = Mockito.mock(GetRestaurantById.class);

        Location location = new Location(37.7749, -122.4194);

        Restaurant restaurant = new Restaurant(
                "1",
                "The Gourmet Bistro",
                location,
                "456 Gourmet Ave",
                DishType.ITALIAN,
                4.5,
                "http://example.com/photo.jpg",
                List.of(new Review("1", "author1", "content1", false)), // userReviews
                new Review("1", "author1", "content1", true)            // summarizedReview
        );

        Mockito.when(getRestaurantByIdMock.execute("1")).thenReturn(Optional.of(restaurant));

        ReviewSummarizeService summarizeService = new GeminiReviewSummarizeService(getRestaurantByIdMock);
        SummarizeReviews summarizeReviews = new SummarizeReviews(summarizeService);
        reviewSummarizeController = new ReviewSummarizeControllerImpl(summarizeReviews, null);

        reviews = Arrays.asList(
                new Review("1", "John Doe", "The food was absolutely fantastic! The steak was cooked to perfection, and the sides complemented it perfectly. The service was attentive but not intrusive. However, the wait time was a bit long, and the prices were on the higher side.", false),
                new Review("1", "Jane Smith", "I had a wonderful dining experience here. The ambiance was great, and the staff was friendly. I particularly enjoyed the grilled salmon, which was tender and flavorful. The dessert was also a highlight. Unfortunately, the noise level was a bit high, which made conversation difficult.", false),
                new Review("1", "Alice Johnson", "The restaurant offers a diverse menu with something for everyone. The pasta dishes were particularly noteworthy, and the homemade bread was a nice touch. While the overall experience was positive, the portion sizes were smaller than expected for the price.", false),
                new Review("1", "Bob Brown", "A solid dining option in the area. The burgers were juicy, and the fries were crispy. The staff was friendly, but there were some issues with the order being incorrect. The atmosphere was relaxed, making it a good spot for a casual meal.", false)
        );
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSummarizeReviews() throws InterruptedException {
        Review summarizedReview = reviewSummarizeController.summarize(reviews);

        assertNotNull(summarizedReview, "Summarized review should not be null.");
        assertTrue(summarizedReview.getContent() != null && !summarizedReview.getContent().isEmpty(), "Summarized review content should not be empty.");
        assertTrue(summarizedReview.isSummarized(), "The summarized review's summarized flag should be true.");
    }
}
