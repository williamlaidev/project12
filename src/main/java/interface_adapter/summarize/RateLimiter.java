package interface_adapter.summarize;

/**
 * Interface for rate limiting requests.
 */
public interface RateLimiter {

    /**
     * Ensures that requests adhere to the specified rate limit.
     * This method blocks the thread if necessary until the required interval between requests has passed.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    long waitIfNecessary() throws InterruptedException;
}
