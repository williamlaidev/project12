package entity.operation_result;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperationResultFailureFactoryTest {

    @Test
    public void testCreateFailureResult() {
        OperationResultFailureFactory factory = new OperationResultFailureFactory();
        OperationResult result = factory.create("Unable to save data.");
        assertFalse(result.isSuccess());
        assertEquals("Failed: Unable to save data.", result.getMessage());
    }
}
