package io.redskap.lambda.runtime;

import io.redskap.lambda.runtime.api.LambdaRuntimeHttpClient;
import io.redskap.lambda.runtime.api.RuntimeInvocationHeaders;
import io.redskap.lambda.runtime.exception.InitializationException;
import io.redskap.lambda.runtime.http.HttpResponse;
import io.redskap.lambda.runtime.internal.RequestHandlerInvoker;
import io.redskap.lambda.runtime.util.EnvironmentVariableResolver;
import io.redskap.lambda.runtime.util.ReservedEnvironmentVariables;
import io.redskap.lambda.runtime.validation.RequestHandlerRegistrationValidator;

import java.util.Collection;

public class LambdaRuntime {
    private final Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations;
    private final RequestHandlerRegistrationValidator registrationValidator;

    public LambdaRuntime(Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations) {
        this(handlerRegistrations, new RequestHandlerRegistrationValidator());
    }

    public LambdaRuntime(Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations, RequestHandlerRegistrationValidator registrationValidator) {
        this.handlerRegistrations = handlerRegistrations;
        this.registrationValidator = registrationValidator;
    }

    public void run() {
        String runtimeApi = EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.AWS_LAMBDA_RUNTIME_API);
        LambdaRuntimeHttpClient runtimeHttpClient = new LambdaRuntimeHttpClient(runtimeApi);
        try {
            RequestHandlerRegistration<?, ?> handlerRegistration = findRequestHandlerRegistration();
            registrationValidator.validate(handlerRegistration);
            while (true) {
                HttpResponse event = runtimeHttpClient.nextInvocation();
                String requestId = event.getHeaders().getSingleValueHeader(RuntimeInvocationHeaders.LAMBDA_RUNTIME_AWS_REQUEST_ID);
                try {
                    String response = RequestHandlerInvoker.invokeHandler(event, handlerRegistration);
                    runtimeHttpClient.invocationResponse(requestId, response);
                } catch (Exception e) {
                    runtimeHttpClient.invocationError(requestId);
                    e.printStackTrace();
                }
            }
        } catch (InitializationException e) {
            e.printStackTrace();
            runtimeHttpClient.initializationError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            runtimeHttpClient.initializationError("Initialization Error");
        }
    }

    private RequestHandlerRegistration<?, ?> findRequestHandlerRegistration() {
        String handler = EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.HANDLER);
        if (handler != null) {
            String handlerClassName = parseHandlerClassName(handler);
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

    protected String parseHandlerClassName(String handler) {
        String[] splitHandler = handler.split("::");
        if (splitHandler.length > 0) {
            return splitHandler[0];
        }
        return null;
    }
}
