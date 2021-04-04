package io.redskap.lambda.runtime.logging;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.io.IOException;

public class LambdaLoggerImpl implements LambdaLogger {
    @Override
    public void log(String message) {
        System.out.print(message);
    }

    @Override
    public void log(byte[] message) {
        try {
            System.out.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing out the log", e);
        }
    }
}
