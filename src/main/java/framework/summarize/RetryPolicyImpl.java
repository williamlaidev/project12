package framework.summarize;

/**
 * Retry policy implementation that defines the maximum number of retry attempts.
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

    /**
     * Returns the maximum number of retry attempts.
     *
     * @return the maximum number of retries
     */
    @Override
    public int getMaxRetries() {
        return maxRetries;
    }
}
