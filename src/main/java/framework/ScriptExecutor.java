package framework;

/**
 * Interface for executing scripts.
 */
public interface ScriptExecutor {

    /**
     * Executes a script with the given input prompt and returns the output.
     *
     * @param inputPrompt the input to pass to the script
     * @return the output of the script
     * @throws Exception if an error occurs during execution
     */
    String execute(String inputPrompt) throws Exception;
}
