package io.redskap.lambda.runtime.internal;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import io.redskap.lambda.runtime.api.RuntimeInvocationHeaders;
import io.redskap.lambda.runtime.http.HttpHeaders;
import io.redskap.lambda.runtime.logging.LambdaLoggerHolder;
import io.redskap.lambda.runtime.util.EnvironmentVariableResolver;
import io.redskap.lambda.runtime.util.ReservedEnvironmentVariables;

import java.util.Calendar;

public class ContextImpl implements Context {
    private HttpHeaders headers;

    public ContextImpl(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public String getAwsRequestId() {
        return headers.getSingleValueHeader(RuntimeInvocationHeaders.LAMBDA_RUNTIME_AWS_REQUEST_ID);
    }

    @Override
    public String getLogGroupName() {
        return EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.AWS_LAMBDA_LOG_GROUP_NAME);
    }

    @Override
    public String getLogStreamName() {
        return EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.AWS_LAMBDA_LOG_STREAM_NAME);
    }

    @Override
    public String getFunctionName() {
        return EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.AWS_LAMBDA_FUNCTION_NAME);
    }

    @Override
    public String getFunctionVersion() {
        return EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.AWS_LAMBDA_FUNCTION_VERSION);
    }

    @Override
    public String getInvokedFunctionArn() {
        return headers.getSingleValueHeader(RuntimeInvocationHeaders.LAMBDA_RUNTIME_INVOKED_FUNCTION_ARN);
    }

    @Override
    public CognitoIdentity getIdentity() {
        // TODO
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ClientContext getClientContext() {
        // TODO
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int getRemainingTimeInMillis() {
        String deadlineMsStr = headers.getSingleValueHeader(RuntimeInvocationHeaders.LAMBDA_RUNTIME_DEADLINE_MS);
        try {
            if (deadlineMsStr != null) {
                long deadlineMs = Long.parseLong(deadlineMsStr);
                long currentMs = currentTime();
                long remainingMs = deadlineMs - currentMs;
                return (int) remainingMs;
            }
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    @Override
    public int getMemoryLimitInMB() {
        String memoryLimit = EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.AWS_LAMBDA_FUNCTION_MEMORY_SIZE);
        if (memoryLimit != null) {
            try {
                return Integer.parseInt(memoryLimit);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    @Override
    public LambdaLogger getLogger() {
        return LambdaLoggerHolder.getLambdaLogger();
    }

    private long currentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
