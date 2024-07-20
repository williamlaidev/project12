package interface_adapter;

/**
 * Interface for summarizing content.
 */
public interface ContentSummarizeController {

    /**
     * Summarizes the provided input string.
     *
     * @param inputString the text to be summarized
     * @return the summarized text
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    String summarize(String inputString) throws InterruptedException;
}
