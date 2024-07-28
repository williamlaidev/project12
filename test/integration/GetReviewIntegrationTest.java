package integration;

import domain.ReviewRetrievalService;
import entity.Review;
import framework.config.EnvConfigService;
import framework.config.EnvConfigServiceImpl;
import framework.search.GooglePlacesReviewsService;
import interface_adapter.search.PlacesReviewsGateways;
import use_case.search.GetReviewsForRestaurant;

import java.util.List;

public class GetReviewIntegrationTest {

    public static void main(String[] args) {
        // Ensure the correct API key is set up in your environment
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        GooglePlacesReviewsService googlePlacesReviewsService = new GooglePlacesReviewsService(envConfigService);
        ReviewRetrievalService reviewRetrievalService = new PlacesReviewsGateways();

        // Instantiate the use case
        GetReviewsForRestaurant getReviewsForRestaurant = new GetReviewsForRestaurant(reviewRetrievalService);

        // Define a place ID to test
        String placeId = "ChIJ7YAIlrM0K4gR-kFykOR3hqw";

        try {
            // Execute the use case to fetch reviews
            List<Review> reviews = getReviewsForRestaurant.execute(placeId);

            // Print out the reviews
            if (reviews.isEmpty()) {
                System.out.println("No reviews found.");
            } else {
                System.out.println("Reviews for place ID " + placeId + ":");
                for (Review review : reviews) {
                    System.out.println(review);
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while fetching reviews: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
