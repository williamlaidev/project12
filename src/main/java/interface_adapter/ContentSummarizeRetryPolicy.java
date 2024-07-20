package interface_adapter;

/**
 * A policy for retrying operations that may fail.
 * This class defines the maximum number of retries allowed for an operation.
 */
public class ContentSummarizeRetryPolicy {

    private final int maxRetries; // Maximum number of retry attempts

    /**
     * Constructs a ContentSummarizeRetryPolicy with the specified maximum retries.
     *
     * @param maxRetries the maximum number of retry attempts allowed
     */
    public ContentSummarizeRetryPolicy(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    /**
     * Gets the maximum number of retry attempts allowed.
     *
     * @return the maximum number of retry attempts
     */
    public int getMaxRetries() {
        return maxRetries;
    }
}
