package app;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class testapi {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "gpt-3.5-turbo";
    private static final int MAX_RETRIES = 5; // Increased retries
    private static final int INITIAL_RETRY_DELAY = 5; // Increased initial delay

    public static void main(String[] args) {
        System.out.println(chatGPT("Who are you?"));
    }

    public static String chatGPT(String message) {
        int retryCount = 0;
        while (retryCount < MAX_RETRIES) {
            try {
                // Create the HTTP POST request
                URL obj = new URL(API_URL);
                HttpURLConnection con = (HttpsURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Authorization", "Bearer " + API_KEY);
                con.setRequestProperty("Content-Type", "application/json; utf-8");

                // Build the request body
                String body = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
                con.setDoOutput(true);
                try (OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream())) {
                    writer.write(body);
                    writer.flush();
                }

                // Check for HTTP response code
                int responseCode = con.getResponseCode();
                if (responseCode == 429) {
                    // Too many requests, retry after some time
                    int retryAfter = con.getHeaderFieldInt("Retry-After", INITIAL_RETRY_DELAY);
                    System.out.println("Rate limit exceeded. Retrying after " + retryAfter + " seconds.");
                    TimeUnit.SECONDS.sleep(retryAfter);
                    retryCount++;
                    continue;
                } else if (responseCode != 200) {
                    throw new IOException("Server returned non-200 response: " + responseCode);
                }

                // Get the response
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    String jsonResponse = response.toString();
                    String content = jsonResponse.split("\"content\":\"")[1].split("\"")[0].substring(4);
                    return content;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                if (retryCount >= MAX_RETRIES) {
                    throw new RuntimeException("Failed after " + MAX_RETRIES + " retries", e);
                }
                retryCount++;
                try {
                    // Exponential backoff with higher initial delay
                    TimeUnit.SECONDS.sleep(INITIAL_RETRY_DELAY * (long) Math.pow(2, retryCount));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
            }
        }
        throw new RuntimeException("Failed to get response after " + MAX_RETRIES + " retries");
    }
}
