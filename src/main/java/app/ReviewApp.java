package app;

import domain.ReviewRepository;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import entity.review.Review;
import framework.EnvConfigService;
import framework.EnvConfigServiceImpl;
import framework.data.DatabaseConfig;
import framework.data.SQLiteReviewRepository;
import framework.search.GooglePlacesReviewClient;
import interface_adapter.data.SQLiteReviewDataAdapter;
import interface_adapter.search.GooglePlacesReviewSearchAdapter;
import interface_adapter.search.GooglePlacesReviewSearchService;
import interface_adapter.search.ReviewSearchGateways;
import use_case.data.create.AddReview;
import use_case.search.FetchRestaurantReviews;

import java.util.List;
import java.util.Scanner;

public class ReviewApp {

    public static void main(String[] args) {
        ReviewApp app = new ReviewApp();
        app.run();
    }

    private void run() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        SQLiteReviewDataAdapter reviewDataAdapter = new SQLiteReviewDataAdapter();
        OperationResultSuccessFactory operationResultSuccessFactory = new OperationResultSuccessFactory();
        OperationResultFailureFactory operationResultFailureFactory = new OperationResultFailureFactory();
        ReviewRepository reviewRepository = new SQLiteReviewRepository(databaseConfig, reviewDataAdapter, operationResultSuccessFactory, operationResultFailureFactory);
        AddReview addReviewUseCase = new AddReview(reviewRepository);
        EnvConfigService envConfigService = new EnvConfigServiceImpl();
        ReviewSearchGateways searchGateways = new GooglePlacesReviewClient(envConfigService);
        GooglePlacesReviewSearchAdapter searchAdapter = new GooglePlacesReviewSearchAdapter();
        GooglePlacesReviewSearchService searchService = new GooglePlacesReviewSearchService(searchAdapter, searchGateways, addReviewUseCase);
        FetchRestaurantReviews fetchReviews = new FetchRestaurantReviews(searchService);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                String restaurantId = promptForRestaurantId(scanner);
                int maxReviews = promptForMaxReviews(scanner);

                List<Review> reviews = fetchReviews.execute(restaurantId, maxReviews);
                displayReviews(reviews);

                System.out.print("Would you like to fetch reviews for another restaurant? (yes/no): ");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    running = false;
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private String promptForRestaurantId(Scanner scanner) {
        System.out.print("Enter the restaurant ID: ");
        return scanner.nextLine();
    }

    private int promptForMaxReviews(Scanner scanner) {
        System.out.print("Enter the maximum number of reviews to fetch: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void displayReviews(List<Review> reviews) {
        System.out.println("Fetched Reviews:");
        for (Review review : reviews) {
            System.out.println("Author: " + review.getAuthor());
            System.out.println("Content: " + review.getContent());
            System.out.println("---------------------------");
        }
    }
}
