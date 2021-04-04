package io.redskap.lambda.runtime.api;

import io.redskap.lambda.runtime.http.HttpClient;
import io.redskap.lambda.runtime.http.HttpResponse;

import java.text.MessageFormat;

public class LambdaRuntimeHttpClient {
    private static final String LAMBDA_VERSION_DATE = "2018-06-01";
    private static final String LAMBDA_NEXT_INVOCATION_TEMPLATE = "http://{0}/{1}/runtime/invocation/next";
    private static final String LAMBDA_INVOCATION_RESPONSE_URL_TEMPLATE = "http://{0}/{1}/runtime/invocation/{2}/response";
    private static final String LAMBDA_INVOCATION_ERROR_URL_TEMPLATE = "http://{0}/{1}/runtime/invocation/{2}/error";
    private static final String LAMBDA_INIT_ERROR_URL_TEMPLATE = "http://{0}/{1}/runtime/init/error";

    private static final String ERROR_RESPONSE_TEMPLATE = "'{'" +
            "\"errorMessage\": \"{0}\"," +
            "\"errorType\": \"{1}\"" +
            "'}'";

    private final HttpClient httpClient;

    private final String runtimeApi;
    private final String nextInvocationUrl;

    public LambdaRuntimeHttpClient(String runtimeApi) {
        this.httpClient = new HttpClient();
        this.runtimeApi = runtimeApi;
        this.nextInvocationUrl = MessageFormat.format(LAMBDA_NEXT_INVOCATION_TEMPLATE, this.runtimeApi, LAMBDA_VERSION_DATE);
    }

    public HttpResponse nextInvocation() {
        return httpClient.get(nextInvocationUrl);
    }

    public HttpResponse invocationResponse(String requestId, String body) {
        String invocationResponseUrl = MessageFormat.format(LAMBDA_INVOCATION_RESPONSE_URL_TEMPLATE, runtimeApi, LAMBDA_VERSION_DATE, requestId);
        return httpClient.post(invocationResponseUrl, body);
    }

    public HttpResponse invocationError(String requestId) {
        String invocationErrorUrl = MessageFormat.format(LAMBDA_INVOCATION_ERROR_URL_TEMPLATE, runtimeApi, LAMBDA_VERSION_DATE, requestId);
        String error = MessageFormat.format(ERROR_RESPONSE_TEMPLATE, "Invocation Error", "RuntimeException");
        return httpClient.post(invocationErrorUrl, error);
    }

    public HttpResponse initializationError(String errorMessage) {
        String initErrorUrl = MessageFormat.format(LAMBDA_INIT_ERROR_URL_TEMPLATE, runtimeApi, LAMBDA_VERSION_DATE);
        String error = MessageFormat.format(ERROR_RESPONSE_TEMPLATE, errorMessage, "InitializationException");
        return httpClient.post(initErrorUrl, error);
    }
}
