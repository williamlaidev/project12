package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * ApiKeyReader class for reading API keys from a specified file.
 */
public class ApiKeyReader {
    private final String apiName;
    private final String apiKeyFilePath;

    /**
     * Constructor to initialize ApiKeyReader with the API name and file path.
     *
     * @param apiName The name of the API.
     * @param apiKeyFilePath The path to the file containing the API keys.
     */
    public ApiKeyReader(String apiName, String apiKeyFilePath) {
        this.apiName = apiName;
        this.apiKeyFilePath = apiKeyFilePath;
    }

    /**
     * Reads the API key from the file.
     *
     * @return The API key as a string.
     * @throws IOException if an I/O error occurs during reading.
     * @throws ApiKeyFileNotFoundException if the API key file is not found.
     * @throws ApiKeyNotFoundException if the API key is not found in the file.
     */
    public String readApiKey() throws IOException, ApiKeyFileNotFoundException, ApiKeyNotFoundException {
        File file = new File(apiKeyFilePath);
        if (!file.exists()) {
            throw new ApiKeyFileNotFoundException(apiKeyFilePath);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String keyPrefix = apiName + "_KEY: ";
            while ((line = br.readLine()) != null) {
                if (line.startsWith(keyPrefix)) {
                    return line.substring(keyPrefix.length()).trim();
                }
            }
        }

        throw new ApiKeyNotFoundException("API key not found for " + apiName);
    }

    /**
     * Exception thrown when the API key file is not found.
     */
    public static class ApiKeyFileNotFoundException extends IOException {
        public ApiKeyFileNotFoundException(String file) {
            super("API key file not found: " + file);
        }
    }

    /**
     * Exception thrown when the API key is not found in the file.
     */
    public static class ApiKeyNotFoundException extends Exception {
        public ApiKeyNotFoundException(String message) {
            super(message);
        }
    }
}
