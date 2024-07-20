package interface_adapter;

import framework.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContentSummarizeControllerTest {

    private ScriptExecutor realExecutor;
    private ContentSummarizeRateLimiter rateLimiter;
    private ContentSummarizeRetryPolicy retryPolicy;
    private ContentSummarizeControllerImpl controller;

    @BeforeEach
    public void setUp() {
        OutputHandler outputHandler = new OutputHandlerImpl();
        realExecutor = new PythonScriptExecutor(outputHandler);
        rateLimiter = new ContentSummarizeRateLimiter(10); // Example rate limit
        retryPolicy = new ContentSummarizeRetryPolicy(3); // Example retry policy

        controller = new ContentSummarizeControllerImpl(realExecutor, rateLimiter, retryPolicy);
    }

    @Test
    public void testSummarizeWithRealScript() throws InterruptedException {
        String input = "Java is a widely-used, object-oriented programming language designed for flexibility and portability. "
                + "It enables developers to write code that runs on any device with a Java Virtual Machine (JVM), "
                + "making it platform-independent. Java's strong typing and object-oriented principles promote "
                + "robust, maintainable, and scalable software development. Its extensive standard library and "
                + "widespread community support contribute to its popularity in various domains, from web applications "
                + "to mobile apps.";

        String result = controller.summarize(input);

        assertNotNull(result, "The result should not be null"); // Check that the result is not null
        assertFalse(result.isEmpty(), "The result should not be empty"); // Ensure the result is not empty
    }
}
