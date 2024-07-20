package framework;

/**
 * Interface for handling output and error messages.
 */
public interface OutputHandler {

    /**
     * Processes standard output.
     *
     * @param output the output message
     */
    void handleOutput(String output);

    /**
     * Processes error messages.
     *
     * @param error the error message
     */
    void handleError(String error);
}