package framework.APIKeyConfig;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Implementation of the {@link EnvConfigService} that loads environment variables
 * from a .env file using the Dotenv library.
 */
public class EnvConfigServiceImpl implements EnvConfigService {
    private final Dotenv dotenv;

    /**
     * Constructs an {@link EnvConfigServiceImpl} instance.
     * Initializes the Dotenv library to load environment variables.
     */
    public EnvConfigServiceImpl() {
        this.dotenv = Dotenv.load();
    }

    /**
     * {@inheritDoc}
     * Retrieves the Gemini API key from the environment variables.
     *
     * @return the Gemini API key as a String
     */
    @Override
    public String getGeminiApiKey() {
        return dotenv.get("GEMINI_API_KEY");
    }

    /**
     * {@inheritDoc}
     * Retrieves the Google Maps API key from the environment variables.
     *
     * @return the Google Maps API key as a String
     */
    @Override
    public String getGoogleMapsApiKey() {
        return dotenv.get("GOOGLE_MAPS_API_KEY");
    }
}
