package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonScriptExecutor implements ScriptExecutor {

    private static final String SCRIPT_PATH = "src/main/java/framework/ContentSummarizerScript.py";
    private static final String PYTHON_COMMAND = "venv/bin/python3";
    private final OutputHandler outputHandler;

    public PythonScriptExecutor(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    @Override
    public String execute(String inputPrompt) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(PYTHON_COMMAND, SCRIPT_PATH, inputPrompt);
        processBuilder.redirectErrorStream(true);

        StringBuilder output = new StringBuilder();

        try {
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append(System.lineSeparator());
                    outputHandler.handleOutput(line); // Handle output via OutputHandler
                }
            }

            int exitCode = process.waitFor();
            String exitMessage = "Exited with code: " + exitCode;
            outputHandler.handleOutput(exitMessage); // Handle exit code via OutputHandler

        } catch (IOException | InterruptedException e) {
            String errorMessage = "Error executing script: " + e.getMessage();
            outputHandler.handleError(errorMessage); // Handle error via OutputHandler
            throw new Exception("Error executing script", e);
        }

        if (outputHandler instanceof OutputHandlerImpl) {
            ((OutputHandlerImpl) outputHandler).printFilteredOutput();
        }

        return output.toString().trim();
    }
}
