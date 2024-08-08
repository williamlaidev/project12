package entity.operation_result;

public class OperationResultSuccessFactory implements OperationResultFactory {
    @Override
    public OperationResult create(String actionMessage) {
        String message = "Succeeded: " + actionMessage;
        return new OperationResult(true, message);
    }
}
