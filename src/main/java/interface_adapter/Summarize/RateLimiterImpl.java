package interface_adapter.Summarize;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Rate limiter implementation for review summarization.
 * This class ensures that requests are limited to a specified rate by enforcing
 * a delay between consecutive requests.
 */
public class RateLimiterImpl implements RateLimiter {

    private final long requestIntervalMs; // Interval between requests in milliseconds
    private Instant lastRequestTime; // Timestamp of the last request

    /**
     * Constructs a RateLimiterImpl with a specified rate limit.
     *
     * @param maxRequestsPerMinute the maximum number of requests allowed per minute
     */
    public RateLimiterImpl(int maxRequestsPerMinute) {
        // Calculate the interval between requests in milliseconds
        this.requestIntervalMs = TimeUnit.MINUTES.toMillis(1) / maxRequestsPerMinute;
        // Initialize the last request time to the current time
        this.lastRequestTime = Instant.now();
    }

    @Override
    public synchronized long waitIfNecessary() throws InterruptedException {
        // Calculate the time elapsed since the last request
        long timeSinceLastRequest = Duration.between(lastRequestTime, Instant.now()).toMillis();

        // If the elapsed time is less than the required interval, wait
        if (timeSinceLastRequest < requestIntervalMs) {
            // Compute the amount of time to wait
            long waitTime = requestIntervalMs - timeSinceLastRequest;
            // Sleep the thread for the calculated wait time
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                // Restore the interrupted status and throw
                Thread.currentThread().interrupt();
                throw e;
            }
            return waitTime;
        }
        // Update the last request time to the current time
        lastRequestTime = Instant.now();
        return 0;
    }
}
