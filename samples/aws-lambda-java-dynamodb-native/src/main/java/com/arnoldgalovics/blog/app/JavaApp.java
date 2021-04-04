package com.arnoldgalovics.blog.app;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.redskap.lambda.runtime.LambdaRuntime;
import io.redskap.lambda.runtime.RequestHandlerRegistration;
import io.redskap.lambda.runtime.validation.RequestHandlerRegistrationValidator;

import java.util.Arrays;

public class JavaApp {
    public static void main(String[] args) {
        new LambdaRuntime(Arrays.asList(
                new RequestHandlerRegistration<>(new IdentityRequestHandler(), String.class, String.class),
                new RequestHandlerRegistration<>(new APIGatewayRequestHandler(), APIGatewayProxyRequestEvent.class, APIGatewayProxyResponseEvent.class),
                new RequestHandlerRegistration<>(new TestRequestHandler(), TestRequest.class, String.class)
        ), new RequestHandlerRegistrationValidator() {
            @Override
            public <I, O> void validate(RequestHandlerRegistration<I, O> registration) {
            }
        }).run();
    }
}
