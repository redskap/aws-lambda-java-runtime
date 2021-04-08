package io.redskap.lambda.runtime.internal;

import io.redskap.lambda.runtime.RequestHandlerRegistration;

import java.util.Collection;

public class HandlerRegistrationResolver {
    public RequestHandlerRegistration<?, ?> resolve(String handlerConfigurationValue, Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations) {
        if (handlerConfigurationValue != null) {
            String handlerClassName = parseHandlerClassName(handlerConfigurationValue);
            if (handlerClassName != null) {
                for (RequestHandlerRegistration<?, ?> registration : handlerRegistrations) {
                    if (handlerClassName.equals(registration.getHandler().getClass().getName())) {
                        return registration;
                    }
                }
            }
        }
        return null;
    }

    private String parseHandlerClassName(String handler) {
        String[] splitHandler = handler.split("::");
        if (splitHandler.length > 0) {
            return splitHandler[0];
        }
        return null;
    }
}
