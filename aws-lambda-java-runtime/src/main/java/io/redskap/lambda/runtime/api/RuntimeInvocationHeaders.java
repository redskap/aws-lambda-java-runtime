package io.redskap.lambda.runtime.api;

public interface RuntimeInvocationHeaders {
    String LAMBDA_RUNTIME_AWS_REQUEST_ID = "Lambda-Runtime-Aws-Request-Id";
    String LAMBDA_RUNTIME_DEADLINE_MS = "Lambda-Runtime-Deadline-Ms";
    String LAMBDA_RUNTIME_INVOKED_FUNCTION_ARN = "Lambda-Runtime-Invoked-Function-Arn";
    String LAMBDA_RUNTIME_TRACE_ID = "Lambda-Runtime-Trace-Id";
    String LAMBDA_RUNTIME_CLIENT_CONTEXT = "Lambda-Runtime-Client-Context";
    String LAMBDA_RUNTIME_COGNITO_IDENTITY = "Lambda-Runtime-Cognito-Identity";
}
