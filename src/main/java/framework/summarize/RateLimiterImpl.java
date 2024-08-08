package framework.summarize;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Rate limiter implementation that enforces a delay between consecutive requests.
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
        this.requestIntervalMs = TimeUnit.MINUTES.toMillis(1) / maxRequestsPerMinute;
        this.lastRequestTime = Instant.now();
    }

    /**
     * Waits if necessary to enforce rate limiting.
     *
     * @return the time waited in milliseconds
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    @Override
    public synchronized long waitIfNecessary() throws InterruptedException {
        long timeSinceLastRequest = Duration.between(lastRequestTime, Instant.now()).toMillis();

        if (timeSinceLastRequest < requestIntervalMs) {
            long waitTime = requestIntervalMs - timeSinceLastRequest;
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw e;
            }
            return waitTime;
        }
        lastRequestTime = Instant.now();
        return 0;
    }
}
