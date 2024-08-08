package entity.operation_result;

/**
 * Represents the result of an operation, including its success status and a message.
 * This class is used to encapsulate the outcome of an operation along with a descriptive message.
 */
public class OperationResult {
    private final boolean success;
    private final String message;

    /**
     * Constructs an OperationResult with the specified success status and message.
     *
     * @param success indicates whether the operation was successful
     * @param message a message describing the result of the operation
     */
    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Returns whether the operation was successful.
     *
     * @return true if the operation was successful; false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns the message describing the result of the operation.
     *
     * @return the message associated with the operation result
     */
    public String getMessage() {
        return message;
    }
}
