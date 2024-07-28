package framework.config;

/**
 * Interface for accessing environment-specific API keys.
 */
public interface EnvConfigService {

    /**
     * Retrieves the Gemini API key.
     *
     * @return the Gemini API key as a String
     */
    String getGeminiApiKey();

    /**
     * Retrieves the Google Maps API key.
     *
     * @return the Google Maps API key as a String
     */
    String getGoogleMapsApiKey();
}
