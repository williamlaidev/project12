package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeminiAPIIntegrationTest {

    @Test
    public void testSummarizeTextIntegration() throws InterruptedException {
        GeminiAPI geminiApi = new GeminiAPIImpl();

        String inputString = "Java's popularity stems from its versatility.  " +
                "As a cross-platform language, it runs seamlessly on Windows, Mac, Linux, " +
                "and even embedded systems like Raspberry Pi. This, coupled with its ease of " +
                "learning and clear object-oriented structure, makes Java a favorite among " +
                "developers.  Its large, open-source community provides ample support and " +
                "resources, while its free and secure nature reduces development costs.  " +
                "Furthermore, Java's speed and power make it suitable for demanding applications. " +
                "The closeness of Java's syntax to C++ and C# allows programmers familiar with " +
                "those languages to easily transition to Java, adding to its overall appeal.";

        // Call the method
        String actualOutput = geminiApi.summarizeText(inputString);

        // Assert that the output is not null
        assertNotNull(actualOutput);

        // Print the actual output for verification
        System.out.println("Actual output: " + actualOutput);
    }
}