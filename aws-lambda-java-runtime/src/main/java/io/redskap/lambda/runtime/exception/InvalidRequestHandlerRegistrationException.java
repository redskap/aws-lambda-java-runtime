package io.redskap.lambda.runtime.exception;

public class InvalidRequestHandlerRegistrationException extends InitializationException {
    public InvalidRequestHandlerRegistrationException(String message) {
        super(message);
    }
}
