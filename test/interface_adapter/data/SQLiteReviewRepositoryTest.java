//package interface_adapter.data;
//
//import entity.Review;
//import entity.ReviewFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SQLiteReviewRepositoryTest {
//    private static final String DB_URL = "jdbc:sqlite::memory:";
//    private SQLiteReviewRepository repository;
//
//    @BeforeEach
//    void setUp() {
//        repository = new SQLiteReviewRepository();
//        try (Connection conn = DriverManager.getConnection(DB_URL)) {
//            String sql = "CREATE TABLE IF NOT EXISTS reviews ("
//                    + "restaurantId TEXT NOT NULL,"
//                    + "author TEXT NOT NULL,"
//                    + "content TEXT NOT NULL,"
//                    + "isSummarized BOOLEAN NOT NULL,"
//                    + "PRIMARY KEY (restaurantId, author)"
//                    + ");";
//            Statement stmt = conn.createStatement();
//            stmt.execute(sql);
//        } catch (SQLException e) {
//            Logger logger = LoggerFactory.getLogger(SQLiteReviewRepositoryTest.class);
//            logger.error("Error creating reviews table", e);
//        }
//    }
//
//    @Test
//    void testAddUserReview() {
//        Review review = ReviewFactory.createUserReview("1", "author1", "Great place!");
//        boolean added = repository.add(review);
//        assertTrue(added, "Review should be added successfully");
//
//        Optional<List<Review>> userReviews = repository.findUserReviews("1");
//        assertTrue(userReviews.isPresent(), "User reviews should be present");
//        assertEquals(1, userReviews.get().size(), "There should be one review for the user");
//        Review retrievedReview = userReviews.get().get(0);
//        assertEquals("author1", retrievedReview.getAuthor(), "Author should match");
//        assertEquals("Great place!", retrievedReview.getContent(), "Content should match");
//    }
//
//    @Test
//    void testAddSummarizedReview() {
//        Review review = ReviewFactory.createGeminiSummarizedReview("1", "Excellent service and food.");
//        boolean added = repository.add(review);
//        assertTrue(added, "Review should be added successfully");
//
//        Optional<Review> summarizedReview = repository.findSummarizedReview("1");
//        assertTrue(summarizedReview.isPresent(), "Summarized review should be present");
//        Review retrievedReview = summarizedReview.get();
//        assertEquals("Excellent service and food.", retrievedReview.getContent(), "Content should match");
//        assertTrue(retrievedReview.isSummarized(), "Review should be summarized");
//    }
//
//    @Test
//    void testFindUserReviews() {
//        Review review1 = ReviewFactory.createUserReview("2", "author2", "Nice ambiance.");
//        Review review2 = ReviewFactory.createUserReview("2", "author3", "Very clean.");
//        repository.add(review1);
//        repository.add(review2);
//
//        Optional<List<Review>> userReviews = repository.findUserReviews("2");
//        assertTrue(userReviews.isPresent(), "User reviews should be present");
//        List<Review> reviews = userReviews.get();
//        assertEquals(2, reviews.size(), "There should be two reviews");
//        assertTrue(reviews.stream().anyMatch(r -> "Nice ambiance.".equals(r.getContent())), "First review content should be found");
//        assertTrue(reviews.stream().anyMatch(r -> "Very clean.".equals(r.getContent())), "Second review content should be found");
//    }
//
//    @Test
//    void testFindSummarizedReview() {
//        Review review = ReviewFactory.createGeminiSummarizedReview("3", "Highly recommended!");
//        repository.add(review);
//
//        Optional<Review> summarizedReview = repository.findSummarizedReview("3");
//        assertTrue(summarizedReview.isPresent(), "Summarized review should be present");
//        assertEquals("Highly recommended!", summarizedReview.get().getContent(), "Content should match");
//    }
//
//    @Test
//    void testUpdateReviewContent() {
//        Review review = ReviewFactory.createUserReview("4", "author4", "Good food.");
//        repository.add(review);
//
//        Review updatedReview = ReviewFactory.createUserReview("4", "author4", "Excellent food.");
//        boolean result = repository.update(updatedReview);
//        assertTrue(result, "Review should be updated successfully");
//
//        Optional<List<Review>> userReviews = repository.findUserReviews("4");
//        assertTrue(userReviews.isPresent(), "User reviews should be present");
//        assertEquals(1, userReviews.get().size(), "There should be one review");
//        assertEquals("Excellent food.", userReviews.get().get(0).getContent(), "Updated content should match");
//    }
//
//    @Test
//    void testDeleteUserReviews() {
//        Review review = ReviewFactory.createUserReview("5", "author5", "Bad service.");
//        repository.add(review);
//
//        boolean result = repository.deleteUserReviews("5");
//        assertTrue(result, "User reviews should be deleted successfully");
//
//        Optional<List<Review>> userReviews = repository.findUserReviews("5");
//        assertFalse(userReviews.isPresent(), "User reviews should not be present");
//    }
//
//    @Test
//    void testDeleteSummarizedReview() {
//        Review review = ReviewFactory.createGeminiSummarizedReview("6", "Summary: Good place.");
//        repository.add(review);
//
//        boolean result = repository.deleteSummarizedReview("6");
//        assertTrue(result, "Summarized review should be deleted successfully");
//
//        Optional<Review> summarizedReview = repository.findSummarizedReview("6");
//        assertFalse(summarizedReview.isPresent(), "Summarized review should not be present");
//    }
//
//    @Test
//    void testDeleteAllReviews() {
//        Review review1 = ReviewFactory.createUserReview("7", "author7", "Amazing food.");
//        Review review2 = ReviewFactory.createGeminiSummarizedReview("7", "Summary: Must try.");
//        repository.add(review1);
//        repository.add(review2);
//
//        boolean result = repository.deleteAllReviews("7");
//        assertTrue(result, "All reviews should be deleted successfully");
//
//        Optional<List<Review>> userReviews = repository.findUserReviews("7");
//        assertFalse(userReviews.isPresent(), "User reviews should not be present");
//
//        Optional<Review> summarizedReview = repository.findSummarizedReview("7");
//        assertFalse(summarizedReview.isPresent(), "Summarized review should not be present");
//    }
//}
