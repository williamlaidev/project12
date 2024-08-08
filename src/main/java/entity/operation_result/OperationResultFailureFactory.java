package entity.operation_result;

public class OperationResultFailureFactory implements OperationResultFactory {
    @Override
    public OperationResult create(String errorMessage) {
        String message = "Failed: " + errorMessage;
        return new OperationResult(false, message);
    }
}
