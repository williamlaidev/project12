package interface_adapter;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class ContentSummarizeRateLimiter {

    private final int maxRequestsPerMinute;
    private final long requestIntervalMs;
    private Instant lastRequestTime;

    public ContentSummarizeRateLimiter(int maxRequestsPerMinute) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.requestIntervalMs = TimeUnit.MINUTES.toMillis(1) / maxRequestsPerMinute;
        this.lastRequestTime = Instant.now();
    }

    public synchronized void waitIfNecessary() throws InterruptedException {
        long timeSinceLastRequest = Duration.between(lastRequestTime, Instant.now()).toMillis();
        if (timeSinceLastRequest < requestIntervalMs) {
            long waitTime = requestIntervalMs - timeSinceLastRequest;
            Thread.sleep(waitTime);
        }
        lastRequestTime = Instant.now();
    }
}
