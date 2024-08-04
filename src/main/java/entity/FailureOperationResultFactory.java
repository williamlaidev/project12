package entity;

public class FailureOperationResultFactory implements OperationResultFactory {
    @Override
    public OperationResult create(String errorMessage) {
        String message = "Failed: " + errorMessage;
        return new OperationResult(false, message);
    }
}
