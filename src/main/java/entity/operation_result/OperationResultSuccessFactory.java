package entity.operation_result;

/**
 * Factory for creating {@link OperationResult} instances representing successful operations.
 * This class prepends "Succeeded: " to the action message to indicate a success result.
 */
public class OperationResultSuccessFactory implements OperationResultFactory {

    /**
     * Creates an {@link OperationResult} representing a success with the specified action message.
     *
     * @param actionMessage a message describing the successful action
     * @return an {@link OperationResult} instance indicating success
     */
    @Override
    public OperationResult create(String actionMessage) {
        String message = "Succeeded: " + actionMessage;
        return new OperationResult(true, message);
    }
}
