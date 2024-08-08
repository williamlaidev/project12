package app;

import domain.RestaurantRepository;
import domain.ReviewSummarizeService;
import entity.review.Review;
import entity.review.ReviewGeminiFactory;
import framework.data.DatabaseConfig;
import framework.data.SQLiteReviewRepository;
import framework.data.SQLiteRestaurantRepository;
import interface_adapter.summarize.GeminiReviewSummarizeAdapter;
import interface_adapter.summarize.GeminiReviewSummarizeService;
import interface_adapter.summarize.ReviewSummarizeAdapter;
import interface_adapter.summarize.ReviewSummarizeGateways;
import use_case.data.create.AddReview;
import use_case.data.read.FindRestaurantById;
import use_case.data.read.FindSummarizedReview;
import use_case.data.read.FindUserReviews;
import use_case.data.update.UpdateReview;
import use_case.summarize.SummarizeReviews;
import framework.summarize.GeminiSummarizeClient;
import entity.operation_result.OperationResultFailureFactory;
import entity.operation_result.OperationResultSuccessFactory;
import interface_adapter.data.SQLiteReviewDataAdapter;

import java.util.List;
import java.util.Scanner;

public class ReviewSummarizerApp {

    public static void main(String[] args) {
        new ReviewSummarizerApp().run();
    }

    private void run() {
        // Initialize components
        DatabaseConfig databaseConfig = new DatabaseConfig();
        ReviewGeminiFactory reviewFactory = new ReviewGeminiFactory();
        SQLiteReviewDataAdapter reviewDataAdapter = new SQLiteReviewDataAdapter();
        OperationResultFailureFactory failureFactory = new OperationResultFailureFactory();
        OperationResultSuccessFactory successFactory = new OperationResultSuccessFactory();
        SQLiteReviewRepository reviewRepository = new SQLiteReviewRepository(databaseConfig, reviewDataAdapter, successFactory, failureFactory);
        RestaurantRepository restaurantRepository = new SQLiteRestaurantRepository();
        FindRestaurantById findRestaurantById = new FindRestaurantById(restaurantRepository);
        FindUserReviews findUserReviews = new FindUserReviews(reviewRepository);
        ReviewSummarizeAdapter summarizeAdapter = new GeminiReviewSummarizeAdapter(findRestaurantById, reviewFactory);
        ReviewSummarizeGateways summarizeGateways = new GeminiSummarizeClient();
        AddReview addReviewUseCase = new AddReview(reviewRepository);
        UpdateReview updateReviewUseCase = new UpdateReview(reviewRepository);
        FindSummarizedReview findSummarizedReview = new FindSummarizedReview(reviewRepository);
        ReviewSummarizeService summarizeService = new GeminiReviewSummarizeService(summarizeGateways, summarizeAdapter, addReviewUseCase, updateReviewUseCase, findSummarizedReview);
        SummarizeReviews summarizeReviews = new SummarizeReviews(summarizeService);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                String restaurantId = promptForRestaurantId(scanner);
                List<Review> reviews = findUserReviews.execute(restaurantId);

                if (reviews.isEmpty()) {
                    System.out.println("No reviews found for restaurant ID: " + restaurantId);
                } else {
                    Review summarizedReview = summarizeReviews.execute(reviews);
                    displaySummary(summarizedReview);
                }

                System.out.print("Would you like to summarize reviews for another restaurant? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                running = response.equals("yes");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private String promptForRestaurantId(Scanner scanner) {
        System.out.print("Enter the restaurant ID: ");
        return scanner.nextLine().trim();
    }

    private void displaySummary(Review summarizedReview) {
        System.out.println("Summarized Review:");
        System.out.println("Author: " + summarizedReview.getAuthor());
        System.out.println("Content: \n" + summarizedReview.getContent());
    }
}
