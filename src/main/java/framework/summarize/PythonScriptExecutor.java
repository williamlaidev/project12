package framework.summarize;

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

    /**
     * Constructs a PythonScriptExecutor with the specified script path.
     *
     * @param scriptPath the path to the Python script to execute
     */
    public PythonScriptExecutor(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    @Override
    public String execute(String inputPrompt) throws Exception {
        File scriptFile = new File(scriptPath);
        if (!scriptFile.exists()) {
            throw new IOException("Script file does not exist: " + scriptPath);
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
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Script exited with code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            throw new Exception("Error executing script", e);
        }

        return output.toString().trim();
    }
}
