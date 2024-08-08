package entity.operation_result;

/**
 * Factory interface for creating {@link OperationResult} instances.
 * This interface defines a method to generate an OperationResult based on a message.
 */
public interface OperationResultFactory {
    /**
     * Creates a new {@link OperationResult} instance with the specified message.
     *
     * @param message a message describing the result of the operation
     * @return an {@link OperationResult} instance with the given message
     */
    OperationResult create(String message);
}
