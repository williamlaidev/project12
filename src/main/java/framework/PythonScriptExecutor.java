package framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Executes a Python script and manages its output and errors.
 */
public class PythonScriptExecutor implements ScriptExecutor {

    private final String scriptPath; // Path to the Python script
    private static final String PYTHON_COMMAND = "myenv/bin/python3";
    private final SummarizationOutputHandler summarizationOutputHandler;

    /**
     * Constructs a PythonScriptExecutor with the specified script path and output handler.
     *
     * @param scriptPath the path to the Python script to execute
     * @param summarizationOutputHandler the handler for script output and errors
     */
    public PythonScriptExecutor(String scriptPath, SummarizationOutputHandler summarizationOutputHandler) {
        this.scriptPath = scriptPath;
        this.summarizationOutputHandler = summarizationOutputHandler;
    }

    @Override
    public String execute(String inputPrompt) throws Exception {
        File scriptFile = new File(scriptPath);
        if (!scriptFile.exists()) {
            System.out.println("Error: The script file does not exist at path: " + scriptPath);
            throw new IOException("Script file does not exist");
        }

        ProcessBuilder processBuilder = new ProcessBuilder(PYTHON_COMMAND, scriptPath, inputPrompt);
        processBuilder.redirectErrorStream(true);

        StringBuilder output = new StringBuilder();

        try {
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append(System.lineSeparator());
                    summarizationOutputHandler.handleOutput(line);
                }
            }

            int exitCode = process.waitFor();
            String exitMessage = "Exited with code: " + exitCode;
            summarizationOutputHandler.handleOutput(exitMessage);

        } catch (IOException | InterruptedException e) {
            String errorMessage = "Error executing script: " + e.getMessage();
            summarizationOutputHandler.handleError(errorMessage);
            throw new Exception("Error executing script", e);
        }

        if (summarizationOutputHandler instanceof SummarizationOutputHandlerImpl) {
            ((SummarizationOutputHandlerImpl) summarizationOutputHandler).printFilteredOutput();
        }

        return output.toString().trim();
    }
}
