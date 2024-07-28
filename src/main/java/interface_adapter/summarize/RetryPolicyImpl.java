package interface_adapter.summarize;

/**
 * Retry policy implementation for review summarization.
 * This class defines the maximum number of retry attempts allowed when performing review summarization.
 */
public class RetryPolicyImpl implements RetryPolicy {

    private final int maxRetries; // Maximum number of retry attempts

    /**
     * Constructs a RetryPolicyImpl with a specified maximum number of retries.
     *
     * @param maxRetries the maximum number of retry attempts
     */
    public RetryPolicyImpl(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public int getMaxRetries() {
        return maxRetries;
    }
}