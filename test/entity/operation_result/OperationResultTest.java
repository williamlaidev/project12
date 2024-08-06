package entity.operation_result;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperationResultTest {

    @Test
    public void testOperationResultSuccess() {
        OperationResult result = new OperationResult(true, "Action completed successfully.");
        assertTrue(result.isSuccess());
        assertEquals("Action completed successfully.", result.getMessage());
    }

    @Test
    public void testOperationResultFailure() {
        OperationResult result = new OperationResult(false, "Action failed.");
        assertFalse(result.isSuccess());
        assertEquals("Action failed.", result.getMessage());
    }
}
