package interface_adapter.summarize;

/**
 * Interface for summarizing reviews using external services or gateways.
 */
public interface ReviewSummarizeGateways {
    /**
     * Summarizes the given review content.
     *
     * @param reviewContent The content to be summarized
     * @return The summarized review content
     * @throws InterruptedException If the summarization process is interrupted
     */
    String summarizeReviews(String reviewContent) throws InterruptedException;
}
