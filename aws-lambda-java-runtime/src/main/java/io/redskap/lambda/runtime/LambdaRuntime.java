package io.redskap.lambda.runtime;

import io.redskap.lambda.runtime.api.LambdaRuntimeHttpClient;
import io.redskap.lambda.runtime.api.RuntimeInvocationHeaders;
import io.redskap.lambda.runtime.exception.InitializationException;
import io.redskap.lambda.runtime.http.HttpResponse;
import io.redskap.lambda.runtime.internal.HandlerRegistrationResolver;
import io.redskap.lambda.runtime.internal.RequestHandlerInvoker;
import io.redskap.lambda.runtime.util.EnvironmentVariableResolver;
import io.redskap.lambda.runtime.util.ReservedEnvironmentVariables;
import io.redskap.lambda.runtime.validation.RequestHandlerRegistrationValidator;

import java.util.Collection;

public class LambdaRuntime {
    private final Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations;
    private final RequestHandlerRegistrationValidator registrationValidator;
    private final HandlerRegistrationResolver handlerRegistrationResolver;

    public LambdaRuntime(Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations) {
        this(handlerRegistrations, new RequestHandlerRegistrationValidator(), new HandlerRegistrationResolver());
    }

    public LambdaRuntime(Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations, RequestHandlerRegistrationValidator registrationValidator,
                         HandlerRegistrationResolver handlerRegistrationResolver) {
        this.handlerRegistrations = handlerRegistrations;
        this.registrationValidator = registrationValidator;
        this.handlerRegistrationResolver = handlerRegistrationResolver;
    }

    public void run() {
        String runtimeApi = EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.AWS_LAMBDA_RUNTIME_API);
        LambdaRuntimeHttpClient runtimeHttpClient = new LambdaRuntimeHttpClient(runtimeApi);
        try {
            String handlerConfigurationValue = EnvironmentVariableResolver.resolve(ReservedEnvironmentVariables.HANDLER);
            RequestHandlerRegistration<?, ?> handlerRegistration = handlerRegistrationResolver.resolve(handlerConfigurationValue, handlerRegistrations);
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
}
