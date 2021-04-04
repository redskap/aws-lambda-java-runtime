package io.redskap.lambda.runtime.sample;

import io.redskap.lambda.runtime.LambdaRuntime;
import io.redskap.lambda.runtime.RequestHandlerRegistration;
import io.redskap.lambda.runtime.validation.RequestHandlerRegistrationValidator;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;

public class NativeDynamoDBApp {
    public static void main(String[] args) {
        new LambdaRuntime(singletonList(
                new RequestHandlerRegistration<>(new TestRequestHandler(), TestRequest.class, String.class)
        ), new RequestHandlerRegistrationValidator(singleton(TestRequest.class))).run();
    }
}
