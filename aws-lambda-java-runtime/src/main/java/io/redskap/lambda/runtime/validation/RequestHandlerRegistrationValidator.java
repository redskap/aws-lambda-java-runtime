package io.redskap.lambda.runtime.validation;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.redskap.lambda.runtime.RequestHandlerRegistration;
import io.redskap.lambda.runtime.exception.InitializationException;
import io.redskap.lambda.runtime.exception.InvalidRequestHandlerRegistrationException;

import java.util.*;

public class RequestHandlerRegistrationValidator {
    private static final Set<Class<?>> SUPPORTED_CLASSES = new HashSet<>();

    static {
        SUPPORTED_CLASSES.add(String.class);
        SUPPORTED_CLASSES.add(APIGatewayProxyRequestEvent.class);
        SUPPORTED_CLASSES.add(APIGatewayProxyResponseEvent.class);
    }

    private final Collection<Class<?>> additionalClasses;

    public RequestHandlerRegistrationValidator() {
        this(Collections.emptyList());
    }

    public RequestHandlerRegistrationValidator(Collection<Class<?>> additionalClasses) {
        this.additionalClasses = additionalClasses;
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
        Class<I> requestType = registration.getRequestType();
        if (!SUPPORTED_CLASSES.contains(requestType) && !additionalClasses.contains(requestType)) {
            throw new InvalidRequestHandlerRegistrationException("Request type " + requestType.getName() + " is not supported");
        }
        Class<O> responseType = registration.getResponseType();
        if (!SUPPORTED_CLASSES.contains(responseType) && !additionalClasses.contains(responseType)) {
            throw new InvalidRequestHandlerRegistrationException("Response type " + responseType.getName() + " is not supported");
        }
    }
}
