package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the GeminiAPI interface using a Python script to summarize text.
 */
public class GeminiAPIImpl implements GeminiAPI {

    // Replace with the path to your Gemini API Python script
    String pythonScriptPath = "src/main/java/api/GeminiAPI.py";

    // Replace with the path to your Python interpreter
    String pythonCommand = "/home/user/java101/project12/myvenv/bin/python";

    // Maximum number of requests per minute allowed by Gemini API
    private static final int MAX_REQUESTS_PER_MINUTE = 6;
    private static final long REQUEST_INTERVAL_MS = TimeUnit.MINUTES.toMillis(1) / MAX_REQUESTS_PER_MINUTE;

    /**
     * Summarizes input text using the Gemini API.
     *
     * @param inputString The text to be summarized.
     * @return The summarized text.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public String summarizeText(String inputString) throws InterruptedException {
        String inputPrompt = "Please summarize the following text in one sentence: " + inputString;

        String output = null;
        Instant lastRequestTime = Instant.now().minusMillis(REQUEST_INTERVAL_MS);
        int maxRetries = 1;
        int retries = 0;

        while (retries < maxRetries) {
            long timeSinceLastRequest = Duration.between(lastRequestTime, Instant.now()).toMillis();
            if (timeSinceLastRequest < REQUEST_INTERVAL_MS) {
                long waitTime = REQUEST_INTERVAL_MS - timeSinceLastRequest;
                System.out.println("Waiting for " + waitTime + " milliseconds before the next request...");
                Thread.sleep(waitTime);
            }

            try {
                output = executeGeminiAPI(inputPrompt);
                if (output != null) {
                    return output;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            lastRequestTime = Instant.now();
            retries++;
            System.out.println("Retry attempt: " + retries);
        }
        System.out.println("Max retries reached. Exiting...");
        return null;
    }

    /**
     * Executes the Gemini API Python script to perform text summarization.
     *
     * @param inputPrompt The prompt passed to the Gemini API script.
     * @return The summarized text.
     * @throws IOException If an I/O error occurs during execution.
     */
    private String executeGeminiAPI(String inputPrompt) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand, pythonScriptPath, inputPrompt);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        System.out.println("Starting Gemini API...");

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (!firstLine) {
                output.append("\n");
            }
            output.append(line);
            firstLine = false;
        }

        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Gemini API Output:");
                System.out.println(output.toString().trim());
                return output.toString().trim();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
