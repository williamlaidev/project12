package interface_adapter.summarize;

public interface ReviewSummarizeGateways {
    String summarizeReviews(String reviewContent) throws InterruptedException;
}