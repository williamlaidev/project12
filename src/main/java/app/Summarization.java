package main.java.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.java.entity.Summary;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;

public class Summarization {

    private static final String API_URL = "https://api.openai.com/v1/completions";

    public static Summary summarizeText(String text) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("prompt", "Summarize the following text:\n\n" + text);
        requestBody.put("max_tokens", 100);
        requestBody.put("temperature", 0.7);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                objectMapper.writeValueAsString(requestBody)
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JsonNode responseJson = objectMapper.readTree(response.body().string());
            String summarizedText = responseJson.get("choices").get(0).get("text").asText().trim();

            return new Summary(0, text, summarizedText, LocalDateTime.now());
        }
    }
}
