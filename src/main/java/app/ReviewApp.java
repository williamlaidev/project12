package app;

import domain.ReviewRetrievalService;
import entity.Review;
import interface_adapter.data.SQLiteReviewRepository;
import interface_adapter.search.PlacesReviewsGateways;
import use_case.data.AddReview;
import use_case.search.GetReviewsForRestaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class ReviewApp {

    private static final Logger logger = LoggerFactory.getLogger(ReviewApp.class);

    public static void main(String[] args) {
        try {
            // Set up services
            SQLiteReviewRepository reviewRepository = new SQLiteReviewRepository();
            AddReview addReviewUseCase = new AddReview(reviewRepository);

            ReviewRetrievalService reviewRetrievalService = new PlacesReviewsGateways(addReviewUseCase); // Inject AddReview
            GetReviewsForRestaurant getReviewsForRestaurant = new GetReviewsForRestaurant(reviewRetrievalService);

            // Read user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Restaurant ID found on the map: ");
            String restaurantId = scanner.nextLine();

            // Fetch reviews
            List<Review> reviews = getReviewsForRestaurant.execute(restaurantId);
            if (reviews.isEmpty()) {
                logger.info("No reviews found.");
            } else {
                // Print out all reviews
                logger.info("All Reviews:");
                for (Review review : reviews) {
                    logger.info(review.toString());
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred: ", e);
        }
    }
}
