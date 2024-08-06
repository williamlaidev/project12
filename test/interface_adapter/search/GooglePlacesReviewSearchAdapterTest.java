package interface_adapter.search;

import entity.review.Review;
import entity.review.ReviewUserFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class GooglePlacesReviewSearchAdapterTest {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesReviewSearchAdapterTest.class);

    @Test
    public void testAdaptToReviewWithValidData() {
        GooglePlacesReviewSearchAdapter adapter = new GooglePlacesReviewSearchAdapter();
        String restaurantId = "restaurant123";
        JSONObject reviewJson = new JSONObject()
                .put("author_name", "Kera Kim")
                .put("text", "Great food and ambiance!");

        Review review = adapter.adaptToReview(reviewJson, restaurantId);

        assertNotNull(review);
        assertEquals("Kera Kim", review.getAuthor());
        assertEquals("Great food and ambiance!", review.getContent());
        assertEquals(restaurantId, review.getRestaurantId());
    }

    @Test
    public void testAdaptToReviewWithMissingAuthor() {
        GooglePlacesReviewSearchAdapter adapter = new GooglePlacesReviewSearchAdapter();
        String restaurantId = "restaurant123";
        JSONObject reviewJson = new JSONObject()
                .put("text", "Great food and ambiance!");

        Review review = adapter.adaptToReview(reviewJson, restaurantId);

        assertNotNull(review);
        assertEquals("Unknown Author", review.getAuthor());
        assertEquals("Great food and ambiance!", review.getContent());
        assertEquals(restaurantId, review.getRestaurantId());
    }

    @Test
    public void testAdaptToReviewWithMissingContent() {
        GooglePlacesReviewSearchAdapter adapter = new GooglePlacesReviewSearchAdapter();
        String restaurantId = "restaurant123";
        JSONObject reviewJson = new JSONObject()
                .put("author_name", "Kera Kim");

        Review review = adapter.adaptToReview(reviewJson, restaurantId);

        assertNotNull(review);
        assertEquals("Kera Kim", review.getAuthor());
        assertEquals("No content available", review.getContent());
        assertEquals(restaurantId, review.getRestaurantId());
    }

    @Test
    public void testAdaptToReviewWithMissingFields() {
        GooglePlacesReviewSearchAdapter adapter = new GooglePlacesReviewSearchAdapter();
        String restaurantId = "restaurant123";
        JSONObject reviewJson = new JSONObject();

        Review review = adapter.adaptToReview(reviewJson, restaurantId);

        assertNotNull(review);
        assertEquals("Unknown Author", review.getAuthor());
        assertEquals("No content available", review.getContent());
        assertEquals(restaurantId, review.getRestaurantId());
    }

    @Test
    public void testAdaptToReviewWithException() {
        GooglePlacesReviewSearchAdapter adapter = new GooglePlacesReviewSearchAdapter();
        String restaurantId = "restaurant123";
        JSONObject reviewJson = new JSONObject() {
            @Override
            public String optString(String key, String defaultValue) {
                throw new RuntimeException("Simulated exception");
            }
        };

        Review review = adapter.adaptToReview(reviewJson, restaurantId);

        assertNull(review);
    }
}
