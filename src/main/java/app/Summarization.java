package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Summary;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Summarization {

    private static final Logger LOGGER = Logger.getLogger(Summarization.class.getName());
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";

    public static Summary summarizeText(String text) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("prompt", "Summarize the following text:\n\n" + text);
        requestBody.put("max_tokens", 100);
        requestBody.put("temperature", 0.7);

        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(requestBody), MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body() != null ? response.body().string() : "";
            JsonNode responseJson = objectMapper.readTree(responseBody);

            JsonNode choices = responseJson.get("choices");
            if (choices == null || !choices.isArray() || choices.isEmpty()) {
                throw new IOException("Invalid response format: 'choices' array is missing or empty");
            }

            String summarizedText = choices.get(0).get("text").asText().trim();
            return new Summary(0, text, summarizedText, LocalDateTime.now());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred", e);
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error occurred", e);
            throw new IOException("Unexpected error occurred while parsing the response", e);
        }
    }

    public static void main(String[] args) {
        // Test the summarizeText method
        String testText = "The restaurant has a cozy atmosphere with excellent service and delicious food. Highly recommended for a nice evening out.";
        try {
            Summary summary = summarizeText(testText);
            System.out.println("Original Text: " + summary.getOriginalText());
            System.out.println("Summarized Text: " + summary.getSummarizedText());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error summarizing text", e);
        }
    }
}
