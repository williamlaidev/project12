package framework.summarize;

import interface_adapter.summarize.ReviewSummarizeGateways;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client for summarizing reviews using a Python script.
 * This client manages rate limiting and retry policies for review summarization.
 */
public class GeminiSummarizeClient implements ReviewSummarizeGateways {

    private static final Logger logger = LoggerFactory.getLogger(GeminiSummarizeClient.class);
    private static final String SCRIPT_PATH = "src/main/java/framework/summarize/ReviewSummarizeScript.py";
    private static final int MAX_REQUESTS_PER_MINUTE = 30; // Maximum number of requests per minute
    private static final int MAX_RETRIES = 3; // Maximum number of retry attempts

    private final ScriptExecutor scriptExecutor;
    private final RateLimiter rateLimiter;
    private final RetryPolicy retryPolicy;

    /**
     * Constructs a GeminiSummarizeClient with default configuration.
     */
    public GeminiSummarizeClient() {
        this.scriptExecutor = new PythonScriptExecutor(SCRIPT_PATH);
        this.rateLimiter = new RateLimiterImpl(MAX_REQUESTS_PER_MINUTE);
        this.retryPolicy = new RetryPolicyImpl(MAX_RETRIES);
    }

    /**
     * Summarizes the provided review content by calling the Python script.
     *
     * @param reviewContent the content of reviews to summarize
     * @return the summarized review content
     * @throws InterruptedException if the thread is interrupted while waiting
     * @throws IllegalArgumentException if reviewContent is null or empty
     */
    @Override
    public String summarizeReviews(String reviewContent) throws InterruptedException {
        if (reviewContent == null || reviewContent.isEmpty()) {
            throw new IllegalArgumentException("Review content cannot be null or empty.");
        }

        String inputPrompt = createInputPrompt(reviewContent);
        return executeWithRetries(inputPrompt);
    }

    /**
     * Creates a prompt for summarizing the reviews.
     *
     * @param reviewContent the content of reviews to summarize
     * @return the generated input prompt
     */
    private String createInputPrompt(String reviewContent) {
        return "Summarize the following reviews briefly, including pros and cons: " + reviewContent;
    }

    /**
     * Executes the summarization script with retry logic.
     *
     * @param inputPrompt the prompt to be sent to the script
     * @return the summarized review content
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    private String executeWithRetries(String inputPrompt) throws InterruptedException {
        int retries = 0;

        while (retries < retryPolicy.getMaxRetries()) {
            rateLimiter.waitIfNecessary();

            try {
                String summarizedContent = scriptExecutor.execute(inputPrompt);
                if (summarizedContent != null) {
                    return summarizedContent;
                }
            } catch (Exception e) {
                logger.error("Error during script execution: {}", e.getMessage(), e);
            }

            retries++;
            if (retries < retryPolicy.getMaxRetries()) {
                long waitTime = rateLimiter.waitIfNecessary();
                logger.info("Retry attempt {} failed. Waiting for {} milliseconds before retrying...", retries, waitTime);
            }
        }

        logger.warn("Failed to summarize reviews after {} attempts.", retryPolicy.getMaxRetries());
        return null;
    }
}
