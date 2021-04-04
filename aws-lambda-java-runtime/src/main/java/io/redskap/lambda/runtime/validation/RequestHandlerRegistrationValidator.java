package io.redskap.lambda.runtime.validation;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.redskap.lambda.runtime.RequestHandlerRegistration;
import io.redskap.lambda.runtime.exception.InitializationException;
import io.redskap.lambda.runtime.exception.InvalidRequestHandlerRegistrationException;

import java.util.HashSet;
import java.util.Set;

public class RequestHandlerRegistrationValidator {
    private static final Set<Class<?>> SUPPORTED_CLASSES = new HashSet<>();

    static {
        SUPPORTED_CLASSES.add(String.class);
        SUPPORTED_CLASSES.add(APIGatewayProxyRequestEvent.class);
        SUPPORTED_CLASSES.add(APIGatewayProxyResponseEvent.class);
    }

    public <I, O> void validate(RequestHandlerRegistration<I, O> registration) {
        validateNotNull(registration);
        validateRegistrationTypes(registration);
    }

    private <I, O> void validateNotNull(RequestHandlerRegistration<I, O> registration) {
        if (registration == null) {
            throw new InitializationException("Handler is not found");
        }
    }

    protected <I, O> void validateRegistrationTypes(RequestHandlerRegistration<I, O> registration) {
        if (!SUPPORTED_CLASSES.contains(registration.getRequestType())) {
            throw new InvalidRequestHandlerRegistrationException("Request type " + registration.getRequestType().getName() + " is not supported");
        }
        if (!SUPPORTED_CLASSES.contains(registration.getResponseType())) {
            throw new InvalidRequestHandlerRegistrationException("Response type " + registration.getRequestType().getName() + " is not supported");
        }
    }
}
