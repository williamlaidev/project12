package entity;

public class SuccessOperationResultFactory implements OperationResultFactory {
    @Override
    public OperationResult create(String actionMessage) {
        String message = "Succeeded: " + actionMessage;
        return new OperationResult(true, message);
    }
}
