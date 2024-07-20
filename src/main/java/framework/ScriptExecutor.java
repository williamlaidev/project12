package framework;

/**
 * Interface for executing scripts.
 */
public interface ScriptExecutor {
    /**
     * Executes a script based on the given input prompt.
     *
     * @param inputPrompt the input prompt for the script execution
     * @return the result of the script execution
     * @throws Exception if an error occurs during script execution
     */
    String execute(String inputPrompt) throws Exception;
}