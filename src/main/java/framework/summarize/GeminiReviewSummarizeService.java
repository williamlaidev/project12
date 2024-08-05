//package framework.summarize;
//
//import domain.ReviewSummarizeService;
//import entity.Review;
//import interface_adapter.summarize.*;
//import use_case.data.FindRestaurantById;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
///**
// * Service for summarizing reviews.
// */
//public class GeminiReviewSummarizeService implements ReviewSummarizeService {
//
//    private static final Logger logger = LoggerFactory.getLogger(GeminiReviewSummarizeService.class);
//
//    private final ScriptExecutor scriptExecutor;
//    private final RateLimiter rateLimiter;
//    private final RetryPolicy retryPolicy;
//    private final ReviewMapper reviewMapper;
//
//    private static final String SCRIPT_PATH = "src/main/java/framework/ReviewSummarizeScript.py";
//
//    /**
//     * Constructs a GeminiReviewSummarizeService with default configurations.
//     *
//     * @param getRestaurantById use case for retrieving restaurant details
//     */
//    public GeminiReviewSummarizeService(FindRestaurantById getRestaurantById) {
//        SummarizationOutputHandler summarizationOutputHandler = new SummarizationOutputHandlerImpl();
//        this.scriptExecutor = new PythonScriptExecutor(SCRIPT_PATH, summarizationOutputHandler);
//        this.rateLimiter = new RateLimiterImpl(30); // 30 requests per minute
//        this.retryPolicy = new RetryPolicyImpl(3); // 3 retries
//        this.reviewMapper = new ReviewMapperImpl(getRestaurantById);
//    }
//
//    @Override
//    public Review summarize(List<Review> reviews) throws InterruptedException {
//        System.out.println("Starting the review summarization process...");
//        logger.info("Starting the review summarization process...");
//
//        // Map the list of reviews to a single input string
//        String inputString = reviewMapper.mapReviewsToString(reviews);
//        System.out.println("Reviews mapped to input string: " + inputString);
//        logger.info("Reviews mapped to input string: {}", inputString);
//
//        // Prepare the input prompt for the script
//        String inputPrompt = "Summarize the following review from the same restaurant, including pros and cons: " + inputString;
//        int retries = 0;
//        String summarizedContent = null;
//
//        // Attempt to summarize the text, retrying if necessary
//        while (retries < retryPolicy.getMaxRetries()) {
//            // Wait if necessary according to the rate limiter policy
//            rateLimiter.waitIfNecessary();
//
//            try {
//                // Execute the script to summarize the text
//                summarizedContent = scriptExecutor.execute(inputPrompt);
//                if (summarizedContent != null) {
//                    break;
//                }
//            } catch (Exception e) {
//                System.err.println("Error during script execution: " + e.getMessage());
//                logger.error("Error during script execution: {}", e.getMessage(), e);
//            }
//
//            retries++;
//            if (retries < retryPolicy.getMaxRetries()) {
//                long waitTime = rateLimiter.waitIfNecessary();
//                System.out.println("Retry attempt " + retries + " failed. Waiting for " + waitTime + " milliseconds before retrying...");
//                logger.info("Retry attempt {} failed. Waiting for {} milliseconds before retrying...", retries, waitTime);
//            }
//        }
//
//        // Return null if all retries fail
//        if (summarizedContent == null) {
//            System.out.println("Failed to summarize review after " + retryPolicy.getMaxRetries() + " attempts.");
//            logger.warn("Failed to summarize review after {} attempts.", retryPolicy.getMaxRetries());
//            return null;
//        }
//
//        // Extract restaurantId from the first review for use in the summarized review
//        String restaurantId = reviews.isEmpty() ? "Unknown" : reviews.get(0).getRestaurantId();
//
//        // Create and return a new Review object with the summarized content
//        return new Review(restaurantId, "Gemini API", summarizedContent, true);
//    }
//}
