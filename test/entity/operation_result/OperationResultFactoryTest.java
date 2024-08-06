package entity.operation_result;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperationResultFactoryTest {

    @Test
    public void testOperationResultSuccessFactory() {
        OperationResultFactory successFactory = new OperationResultSuccessFactory();
        OperationResult result = successFactory.create("Action completed successfully.");
        assertTrue(result.isSuccess());
        assertEquals("Succeeded: Action completed successfully.", result.getMessage());
    }

    @Test
    public void testOperationResultFailureFactory() {
        OperationResultFactory failureFactory = new OperationResultFailureFactory();
        OperationResult result = failureFactory.create("Action failed.");
        assertFalse(result.isSuccess());
        assertEquals("Failed: Action failed.", result.getMessage());
    }
}
