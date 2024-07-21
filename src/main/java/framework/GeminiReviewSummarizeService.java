package framework;

import domain.ReviewSummarizeService;
import entity.Review;
import interface_adapter.*;
import use_case.GetRestaurantById;

import java.util.List;

/**
 * Service for summarizing reviews.
 */
public class GeminiReviewSummarizeService implements ReviewSummarizeService {

    private final ScriptExecutor scriptExecutor;
    private final RateLimiter rateLimiter;
    private final RetryPolicy retryPolicy;
    private final ReviewMapper reviewMapper;

    private static final String SCRIPT_PATH = "src/main/java/framework/ReviewSummarizeScript.py";

    /**
     * Constructs a GeminiReviewSummarizeService with default configurations.
     *
     * @param getRestaurantById use case for retrieving restaurant details
     */
    public GeminiReviewSummarizeService(GetRestaurantById getRestaurantById) {
        OutputHandler outputHandler = new OutputHandlerImpl();
        this.scriptExecutor = new PythonScriptExecutor(SCRIPT_PATH, outputHandler);
        this.rateLimiter = new ReviewSummarizeRateLimiter(30); // 30 requests per minute
        this.retryPolicy = new ReviewSummarizeRetryPolicy(3); // 3 retries
        this.reviewMapper = new ReviewMapperImpl(getRestaurantById);
    }

    @Override
    public Review summarize(List<Review> reviews) throws InterruptedException {
        System.out.println("Starting the review summarization process...");

        // Map the list of reviews to a single input string
        String inputString = reviewMapper.mapReviewsToString(reviews);
        System.out.println("Reviews mapped to input string: " + inputString);

        // Prepare the input prompt for the script
        String inputPrompt = "Summarize the following review from the same restaurant, including pros and cons: " + inputString;
        int retries = 0;
        String summarizedContent = null;

        // Attempt to summarize the text, retrying if necessary
        while (retries < retryPolicy.getMaxRetries()) {
            // Wait if necessary according to the rate limiter policy
            rateLimiter.waitIfNecessary();

            try {
                // Execute the script to summarize the text
                summarizedContent = scriptExecutor.execute(inputPrompt);
                if (summarizedContent != null) {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Error during script execution: " + e.getMessage());
                e.printStackTrace();
            }

            retries++;
            if (retries < retryPolicy.getMaxRetries()) {
                long waitTime = rateLimiter.waitIfNecessary();
                System.out.println("Retry attempt " + retries + " failed. Waiting for " + waitTime + " milliseconds before retrying...");
            }
        }

        // Return null if all retries fail
        if (summarizedContent == null) {
            System.out.println("Failed to summarize review after " + retryPolicy.getMaxRetries() + " attempts.");
            return null;
        }

        // Extract restaurantId from the first review for use in the summarized review
        String restaurantId = reviews.isEmpty() ? "Unknown" : reviews.get(0).getRestaurantId();

        // Create and return a new Review object with the summarized content
        return new Review(restaurantId, "Gemini API", summarizedContent, true);
    }
}
