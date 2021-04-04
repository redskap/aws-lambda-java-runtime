package io.redskap.lambda.runtime.util;

public class EnvironmentVariableResolver {
    public static String resolve(String key) {
        return System.getenv(key);
    }
}
