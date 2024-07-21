package interface_adapter;

/**
 * Retry policy implementation for review summarization.
 * This class defines the maximum number of retry attempts allowed when performing review summarization.
 */
public class ReviewSummarizeRetryPolicy implements RetryPolicy {

    private final int maxRetries; // Maximum number of retry attempts

    /**
     * Constructs a ReviewSummarizeRetryPolicy with a specified maximum number of retries.
     *
     * @param maxRetries the maximum number of retry attempts
     */
    public ReviewSummarizeRetryPolicy(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public int getMaxRetries() {
        return maxRetries;
    }
}