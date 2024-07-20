package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Executes a Python script and manages its output and errors.
 * This class utilizes a ProcessBuilder to run a Python script with a given input prompt.
 * Output and error streams are processed by the provided OutputHandler.
 */
public class PythonScriptExecutor implements ScriptExecutor {

    private static final String SCRIPT_PATH = "src/main/java/framework/ContentSummarizerScript.py"; // Replace with the path to the Python script
    private static final String PYTHON_COMMAND = "venv/bin/python3"; // Replace with the path to the Python interpreter
    private final OutputHandler outputHandler;

    /**
     * Constructs a PythonScriptExecutor with the specified OutputHandler.
     *
     * @param outputHandler the handler responsible for processing script output and errors
     */
    public PythonScriptExecutor(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    /**
     * Executes the Python script with the given input prompt.
     *
     * @param inputPrompt the input prompt to be passed to the Python script
     * @return the output produced by the script as a String
     * @throws Exception if an error occurs during script execution
     */
    @Override
    public String execute(String inputPrompt) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(PYTHON_COMMAND, SCRIPT_PATH, inputPrompt);
        processBuilder.redirectErrorStream(true); // Merge error stream with output stream

        StringBuilder output = new StringBuilder(); // StringBuilder to capture the script output

        try {
            Process process = processBuilder.start();

            // Read the output of the process
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append(System.lineSeparator());
                    outputHandler.handleOutput(line); // Process output through OutputHandler
                }
            }

            // Wait for the process to complete and get the exit code
            int exitCode = process.waitFor();
            String exitMessage = "Exited with code: " + exitCode;
            outputHandler.handleOutput(exitMessage);

        } catch (IOException | InterruptedException e) {
            // Handle exceptions that occur during process execution
            String errorMessage = "Error executing script: " + e.getMessage();
            outputHandler.handleError(errorMessage);
            throw new Exception("Error executing script", e);
        }

        if (outputHandler instanceof OutputHandlerImpl) {
            ((OutputHandlerImpl) outputHandler).printFilteredOutput();
        }

        return output.toString().trim();
    }
}
