package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GeminiAPITest {

    private GeminiAPI geminiApi;

    @BeforeEach
    public void setUp() {
        geminiApi = Mockito.mock(GeminiAPI.class);
    }

    @Test
    public void testSummarizeText() throws InterruptedException {
        // Mock the API response
        String inputString = "This is a test input string that needs to be summarized.";
        String expectedOutput = "This is a summary of the input string.";

        when(geminiApi.summarizeText(anyString())).thenReturn(expectedOutput);

        // Call the method
        String actualOutput = geminiApi.summarizeText(inputString);

        // Verify the result
        assertEquals(expectedOutput, actualOutput);

        // Verify that the method was called once with the specified input
        verify(geminiApi, times(1)).summarizeText(inputString);
    }
}