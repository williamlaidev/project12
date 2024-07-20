package interface_adapter;

public class ContentSummarizeRetryPolicy {

    private final int maxRetries;

    public ContentSummarizeRetryPolicy(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getMaxRetries() {
        return maxRetries;
    }
}
