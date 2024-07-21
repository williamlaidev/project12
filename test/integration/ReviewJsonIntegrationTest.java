package integration;

import data_access.ReviewDataAccess;
import domain.ReviewRepository;
import entity.Review;
import framework.JsonReviewDataAccess;
import interface_adapter.InMemoryReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.*;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewJsonIntegrationTest {

    private static final String JSON_FILE_PATH = "src/resources/data/reviews.json";
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        ReviewDataAccess reviewDataAccess = new JsonReviewDataAccess();
        reviewRepository = new InMemoryReviewRepository(reviewDataAccess);

        File jsonFile = new File(JSON_FILE_PATH);
        if (jsonFile.exists()) {
            if (!jsonFile.delete()) {
                fail("Failed to delete existing JSON file: " + JSON_FILE_PATH);
            }
        }
    }

    @AfterEach
    public void tearDown() {
        File jsonFile = new File(JSON_FILE_PATH);
        if (jsonFile.exists()) {
            if (!jsonFile.delete()) {
                fail("Failed to delete JSON file: " + JSON_FILE_PATH);
            }
        }
    }

    @Test
    public void testAddReview() {
        AddReview addReviewUseCase = new AddReview(reviewRepository);
        Review review1 = new Review("1", "Author1", "Great place!", false);
        Review review2 = new Review("2", "Author2", "Nice ambiance.", false);
        Review review3 = new Review("3", "Author3", "Could be better.", true);

        Review result1 = addReviewUseCase.execute(review1);
        Review result2 = addReviewUseCase.execute(review2);
        Review result3 = addReviewUseCase.execute(review3);

        assertNotNull(result1, "Failed to add review 1.");
        assertEquals(review1, result1, "Added review 1 does not match expected review.");

        assertNotNull(result2, "Failed to add review 2.");
        assertEquals(review2, result2, "Added review 2 does not match expected review.");

        assertNotNull(result3, "Failed to add review 3.");
        assertEquals(review3, result3, "Added review 3 does not match expected review.");

        // Try adding the same review again and expect failure
        Review duplicateResult = addReviewUseCase.execute(review1);
        assertNull(duplicateResult, "Adding a review with a duplicate ID should fail.");
    }

    @Test
    public void testGetReviewByRestaurant() {
        AddReview addReviewUseCase = new AddReview(reviewRepository);
        Review review1 = new Review("1", "Author1", "Great place!", false);
        Review review2 = new Review("1", "Author2", "Nice ambiance.", false);
        addReviewUseCase.execute(review1);
        addReviewUseCase.execute(review2);

        GetReviewByRestaurant getReviewByRestaurantUseCase = new GetReviewByRestaurant(reviewRepository);
        Optional<Review> retrievedReview1 = getReviewByRestaurantUseCase.execute("1");
        Optional<Review> retrievedReview2 = getReviewByRestaurantUseCase.execute("2");

        // Verify that the review for restaurant with ID '1' is found
        assertTrue(retrievedReview1.isPresent(), "Review for restaurant with ID '1' was not found.");
        assertEquals(review1, retrievedReview1.get(), "The retrieved review does not match the expected review.");

        // Verify that no review is found for a non-existent restaurant
        assertFalse(retrievedReview2.isPresent(), "Reviews should not be found for a non-existent restaurant.");
    }

    @Test
    public void testUpdateReview() {
        AddReview addReviewUseCase = new AddReview(reviewRepository);
        Review review = new Review("1", "Author1", "Great place!", false);
        addReviewUseCase.execute(review);

        UpdateReview updateReviewUseCase = new UpdateReview(reviewRepository);
        Review updatedReview = new Review("1", "Author1", "Awesome place!", true);
        boolean updateResult = updateReviewUseCase.execute(updatedReview);
        assertTrue(updateResult, "Failed to update review.");

        // Verify review is updated
        GetReviewByRestaurant getReviewByRestaurantUseCase = new GetReviewByRestaurant(reviewRepository);
        Optional<Review> retrievedReview = getReviewByRestaurantUseCase.execute("1");
        assertTrue(retrievedReview.isPresent(), "Review with ID '1' was not found.");
        assertEquals(updatedReview, retrievedReview.get(), "Updated review does not match the retrieved review.");

        // Try updating a non-existent review
        Review nonExistentReview = new Review("2", "Author2", "Non-existent review", false);
        boolean nonExistentUpdateResult = updateReviewUseCase.execute(nonExistentReview);
        assertFalse(nonExistentUpdateResult, "Updating a non-existent review should fail.");
    }

    @Test
    public void testDeleteReviewByRestaurant() {
        AddReview addReviewUseCase = new AddReview(reviewRepository);
        Review review = new Review("1", "Author1", "Great place!", false);
        addReviewUseCase.execute(review);

        DeleteReviewByRestaurant deleteReviewByRestaurantUseCase = new DeleteReviewByRestaurant(reviewRepository);
        boolean deleteResult = deleteReviewByRestaurantUseCase.execute("1");
        assertTrue(deleteResult, "Failed to delete review with ID: 1");

        // Verify review is deleted
        GetReviewByRestaurant getReviewByRestaurantUseCase = new GetReviewByRestaurant(reviewRepository);
        Optional<Review> retrievedReview = getReviewByRestaurantUseCase.execute("1");
        assertFalse(retrievedReview.isPresent(), "Review with ID '1' was found after deletion.");
    }

    @Test
    public void testDeleteReviewBySummarized() {
        AddReview addReviewUseCase = new AddReview(reviewRepository);
        Review summarizedReview = new Review("1", "Author1", "Great place!", true);
        Review nonSummarizedReview = new Review("2", "Author2", "Okay place.", false);
        addReviewUseCase.execute(summarizedReview);
        addReviewUseCase.execute(nonSummarizedReview);

        DeleteReviewBySummarized deleteReviewBySummarizedUseCase = new DeleteReviewBySummarized(reviewRepository);
        boolean deleteResult = deleteReviewBySummarizedUseCase.execute(true);
        assertTrue(deleteResult, "Failed to delete summarized reviews.");

        // Verify summarized reviews are deleted
        GetReviewByRestaurant getReviewByRestaurantUseCase = new GetReviewByRestaurant(reviewRepository);
        Optional<Review> retrievedSummarizedReview = getReviewByRestaurantUseCase.execute("1");
        assertFalse(retrievedSummarizedReview.isPresent(), "Summarized review with ID '1' was found after deletion.");

        // Verify non-summarized reviews are not deleted
        Optional<Review> retrievedNonSummarizedReview = getReviewByRestaurantUseCase.execute("2");
        assertTrue(retrievedNonSummarizedReview.isPresent(), "Non-summarized review with ID '2' was not found.");
    }
}
