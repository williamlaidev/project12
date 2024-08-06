package interface_adapter.summarize;

import entity.restaurant.Restaurant;
import entity.review.Review;
import entity.review.ReviewGeminiFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.data.read.FindRestaurantById;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GeminiReviewSummarizeAdapterTest {

    private FindRestaurantById findRestaurantById;
    private ReviewGeminiFactory reviewFactory;
    private GeminiReviewSummarizeAdapter adapter;

    @BeforeEach
    public void setUp() {
        findRestaurantById = mock(FindRestaurantById.class);
        reviewFactory = new ReviewGeminiFactory();
        adapter = new GeminiReviewSummarizeAdapter(findRestaurantById, reviewFactory);
    }

    @Test
    public void testAdaptToReviewStringWithNoReviews() {
        List<Review> reviews = List.of();
        String expected = "This restaurant have no reviews";
        String result = adapter.adaptToReviewString(reviews);
        assertEquals(expected, result);
    }

    @Test
    public void testAdaptToReviewStringWithUnknownRestaurant() {
        List<Review> reviews = List.of(
                new Review("temporary_id_2", "Kera Kim", "Great food!", false)
        );
        when(findRestaurantById.execute(anyString())).thenReturn(Optional.empty());

        String expected = "Unknown Restaurant:\n- Great food!";
        String result = adapter.adaptToReviewString(reviews);
        assertEquals(expected, result);
    }

    @Test
    public void testAdaptToReviewStringWithReviews() {
        List<Review> reviews = List.of(
                new Review("temporary_id_1", "Kera Kim", "Great food!", false),
                new Review("temporary_id_1", "Jenny Lee", "Lovely ambiance.", false)
        );
        Restaurant restaurant = new Restaurant("temporary_id_1", "Restaurant 1", new entity.location.Location(0, 0), "123 Main Street", entity.DishType.CHINESE, 4.5, "http://example.com/photo.jpg");
        when(findRestaurantById.execute(anyString())).thenReturn(Optional.of(restaurant));

        String expected = "Restaurant 1:\n- Great food!\n- Lovely ambiance.";
        String result = adapter.adaptToReviewString(reviews);

        assertEquals(expected, result);
    }

    @Test
    public void testAdaptToReview() {
        String restaurantId = "temporary_id_1";
        String content = "Summarized review content.";
        Review review = adapter.adaptToReview(restaurantId, content);

        assertEquals(restaurantId, review.getRestaurantId());
        assertEquals(content, review.getContent());
        assertEquals("Gemini", review.getAuthor());
        assertTrue(review.isSummarized());
    }
}
