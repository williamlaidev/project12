package interface_adapter.Summarize;

/**
 * Interface for defining retry policies.
 */
public interface RetryPolicy {

    /**
     * Gets the maximum number of retry attempts allowed.
     *
     * @return the maximum number of retry attempts
     */
    int getMaxRetries();
}
