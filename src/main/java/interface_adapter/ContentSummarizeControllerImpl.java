package interface_adapter;

import framework.ScriptExecutor;

/**
 * Implementation of the ContentSummarizeController interface.
 * This class uses a ScriptExecutor to execute a summarization script and manages retries and rate limiting.
 */
public class ContentSummarizeControllerImpl implements ContentSummarizeController {

    private final ScriptExecutor scriptExecutor; // Used to execute the summarization script
    private final ContentSummarizeRateLimiter rateLimiter; // Manages rate limiting to avoid overloading
    private final ContentSummarizeRetryPolicy retryPolicy; // Defines the retry policy for failed executions

    /**
     * Constructs a ContentSummarizeControllerImpl with the specified dependencies.
     *
     * @param scriptExecutor the ScriptExecutor used to run the summarization script
     * @param rateLimiter the ContentSummarizeRateLimiter used to manage request rate
     * @param retryPolicy the ContentSummarizeRetryPolicy that determines retry behavior
     */
    public ContentSummarizeControllerImpl(ScriptExecutor scriptExecutor, ContentSummarizeRateLimiter rateLimiter, ContentSummarizeRetryPolicy retryPolicy) {
        this.scriptExecutor = scriptExecutor;
        this.rateLimiter = rateLimiter;
        this.retryPolicy = retryPolicy;
    }

    /**
     * Summarizes the given input string by passing it to a script executor.
     * Manages retries and rate limiting based on the configured policies.
     *
     * @param inputString the text to be summarized
     * @return the summarized text, or null if the operation fails after retries
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @Override
    public String summarize(String inputString) throws InterruptedException {
        // Prepare the input prompt for the summarization script
        String inputPrompt = "Summarize the following text: " + inputString;
        int retries = 0;

        // Attempt to summarize the text, retrying if necessary
        while (retries < retryPolicy.getMaxRetries()) {
            // Wait if necessary according to the rate limiter policy
            rateLimiter.waitIfNecessary();

            try {
                // Execute the script to summarize the text
                String output = scriptExecutor.execute(inputPrompt);
                if (output != null) {
                    return output;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            retries++;
        }

        // Return null if all retries fail
        return null;
    }
}