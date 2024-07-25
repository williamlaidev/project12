package app;

import data_access.ReviewDataAccess;
import domain.ReviewRepository;
import domain.ReviewRetrievalService;
import entity.Review;
import framework.Data.JsonReviewDataAccess;
import interface_adapter.Data.InMemoryReviewRepository;
import interface_adapter.Search.PlacesReviewsGateways;
import use_case.Data.AddReview;
import use_case.Search.GetReviewsForRestaurant;

import java.util.List;
import java.util.Scanner;

public class ReviewApp {

    public static void main(String[] args) {
        try {
            // Set up services
            ReviewDataAccess reviewDataAccess = new JsonReviewDataAccess();
            ReviewRepository reviewRepository = new InMemoryReviewRepository(reviewDataAccess);
            AddReview addReviewUseCase = new AddReview(reviewRepository);

            ReviewRetrievalService reviewRetrievalService = new PlacesReviewsGateways();
            GetReviewsForRestaurant getReviewsForRestaurant = new GetReviewsForRestaurant(reviewRetrievalService);

            // Read user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Restaurant ID found on the map: ");
            String restaurantId = scanner.nextLine();

            // Fetch reviews
            List<Review> reviews = getReviewsForRestaurant.execute(restaurantId);
            if (reviews.isEmpty()) {
                System.out.println("No reviews found.");
            } else {
                // Add reviews to repository
                for (Review review : reviews) {
                    addReviewUseCase.execute(review);
                }

                // Print out all reviews
                System.out.println("All Reviews:");
                for (Review review : reviews) {
                    System.out.println(review);
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
