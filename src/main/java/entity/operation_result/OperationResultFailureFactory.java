package entity.operation_result;

/**
 * Factory for creating {@link OperationResult} instances representing failed operations.
 * This class prepends "Failed: " to the error message to indicate a failure result.
 */
public class OperationResultFailureFactory implements OperationResultFactory {

    /**
     * Creates an {@link OperationResult} representing a failure with the specified error message.
     *
     * @param errorMessage a message describing the failure
     * @return an {@link OperationResult} instance indicating failure
     */
    @Override
    public OperationResult create(String errorMessage) {
        String message = "Failed: " + errorMessage;
        return new OperationResult(false, message);
    }
}
