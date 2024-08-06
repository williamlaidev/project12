package framework.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnvConfigServiceTest {

    private EnvConfigServiceImpl envConfigService;
    private Dotenv dotenvMock;

    @BeforeEach
    public void setUp() {
        dotenvMock = mock(Dotenv.class);
        try (MockedStatic<Dotenv> mocked = Mockito.mockStatic(Dotenv.class)) {
            mocked.when(Dotenv::load).thenReturn(dotenvMock);
            envConfigService = new EnvConfigServiceImpl();
        }
    }

    @Test
    public void testGetGeminiApiKey() {
        String expectedApiKey = "test_gemini_api_key";
        when(dotenvMock.get("GEMINI_API_KEY")).thenReturn(expectedApiKey);

        String actualApiKey = envConfigService.getGeminiApiKey();
        assertEquals(expectedApiKey, actualApiKey);
    }

    @Test
    public void testGetGoogleMapsApiKey() {
        String expectedApiKey = "test_google_maps_api_key";
        when(dotenvMock.get("GOOGLE_MAPS_API_KEY")).thenReturn(expectedApiKey);

        String actualApiKey = envConfigService.getGoogleMapsApiKey();
        assertEquals(expectedApiKey, actualApiKey);
    }

    @Test
    public void testGetGeminiApiKeyNotFound() {
        when(dotenvMock.get("GEMINI_API_KEY")).thenReturn(null);

        String actualApiKey = envConfigService.getGeminiApiKey();
        assertNull(actualApiKey);
    }

    @Test
    public void testGetGoogleMapsApiKeyNotFound() {
        when(dotenvMock.get("GOOGLE_MAPS_API_KEY")).thenReturn(null);

        String actualApiKey = envConfigService.getGoogleMapsApiKey();
        assertNull(actualApiKey);
    }
}
