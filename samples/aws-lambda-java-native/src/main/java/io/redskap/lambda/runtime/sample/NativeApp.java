package io.redskap.lambda.runtime.sample;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.redskap.lambda.runtime.LambdaRuntime;
import io.redskap.lambda.runtime.RequestHandlerRegistration;

import static java.util.Arrays.asList;

public class NativeApp {
    public static void main(String[] args) {
        new LambdaRuntime(asList(
                new RequestHandlerRegistration<>(new APIGatewayRequestHandler(), APIGatewayProxyRequestEvent.class, APIGatewayProxyResponseEvent.class),
                new RequestHandlerRegistration<>(new IdentityRequestHandler(), String.class, String.class)
        )).run();
    }
}
