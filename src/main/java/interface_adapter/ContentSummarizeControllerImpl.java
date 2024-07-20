package interface_adapter;

import framework.ScriptExecutor;

public class ContentSummarizeControllerImpl implements ContentSummarizeController {

    private final ScriptExecutor scriptExecutor;
    private final ContentSummarizeRateLimiter rateLimiter;
    private final ContentSummarizeRetryPolicy retryPolicy;

    public ContentSummarizeControllerImpl(ScriptExecutor scriptExecutor, ContentSummarizeRateLimiter rateLimiter, ContentSummarizeRetryPolicy retryPolicy) {
        this.scriptExecutor = scriptExecutor;
        this.rateLimiter = rateLimiter;
        this.retryPolicy = retryPolicy;
    }

    @Override
    public String summarize(String inputString) throws InterruptedException {
        String inputPrompt = "Summarize the following text: " + inputString;
        int retries = 0;

        while (retries < retryPolicy.getMaxRetries()) {
            rateLimiter.waitIfNecessary();

            try {
                String output = scriptExecutor.execute(inputPrompt);
                if (output != null) {
                    return output;
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Consider logging the exception
            }

            retries++;
        }

        return null;
    }
}
