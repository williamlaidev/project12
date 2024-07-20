package interface_adapter;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * A rate limiter to control the frequency of requests.
 * This class ensures that requests are limited to a specified rate.
 */
public class ContentSummarizeRateLimiter {

    private final long requestIntervalMs; // Minimum interval between requests in milliseconds
    private Instant lastRequestTime; // Timestamp of the last request

    /**
     * Constructs a ContentSummarizeRateLimiter with the specified rate limit.
     *
     * @param maxRequestsPerMinute the maximum number of requests allowed per minute
     */
    public ContentSummarizeRateLimiter(int maxRequestsPerMinute) {
        // Calculate the interval between requests based on the maximum requests per minute
        this.requestIntervalMs = TimeUnit.MINUTES.toMillis(1) / maxRequestsPerMinute;
        // Initialize the timestamp of the last request to the current time
        this.lastRequestTime = Instant.now();
    }

    /**
     * Ensures that requests adhere to the specified rate limit.
     * This method blocks the thread if necessary until the required interval between requests has passed.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public synchronized void waitIfNecessary() throws InterruptedException {
        // Calculate the time elapsed since the last request
        long timeSinceLastRequest = Duration.between(lastRequestTime, Instant.now()).toMillis();
        // If the elapsed time is less than the required interval, sleep for the remaining time
        if (timeSinceLastRequest < requestIntervalMs) {
            long waitTime = requestIntervalMs - timeSinceLastRequest;
            Thread.sleep(waitTime);
        }
        // Update the timestamp of the last request to the current time
        lastRequestTime = Instant.now();
    }
}
