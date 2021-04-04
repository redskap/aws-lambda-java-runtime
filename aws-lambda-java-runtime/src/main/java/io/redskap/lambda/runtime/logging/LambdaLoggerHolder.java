package io.redskap.lambda.runtime.logging;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class LambdaLoggerHolder {
    private static final LambdaLogger LAMBDA_LOGGER = new LambdaLoggerImpl();

    public static LambdaLogger getLambdaLogger() {
        return LAMBDA_LOGGER;
    }
}
