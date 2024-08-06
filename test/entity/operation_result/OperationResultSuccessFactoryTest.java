package entity.operation_result;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperationResultSuccessFactoryTest {

    @Test
    public void testCreateSuccessResult() {
        OperationResultSuccessFactory factory = new OperationResultSuccessFactory();
        OperationResult result = factory.create("Data saved successfully.");
        assertTrue(result.isSuccess());
        assertEquals("Succeeded: Data saved successfully.", result.getMessage());
    }
}
