package io.redskap.lambda.runtime.internal;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.redskap.lambda.runtime.RequestHandlerRegistration;
import io.redskap.lambda.runtime.http.HttpResponse;
import io.redskap.lambda.runtime.util.JsonMapper;

public class RequestHandlerInvoker {
    public static <I, O> String invokeHandler(HttpResponse event, RequestHandlerRegistration<I, O> registration) {
        return internalInvokeHandler(event, registration.getHandler(), registration.getRequestType(), registration.getResponseType());
    }

    private static <I, O> String internalInvokeHandler(HttpResponse event, RequestHandler<I, O> handler, Class<I> requestType, Class<O> responseType) {
        I request;
        if (!String.class.equals(requestType)) {
            request = JsonMapper.fromJson(event.getBody(), requestType);
        } else {
            request = (I) event.getBody();
        }
        O response = handler.handleRequest(request, new ContextImpl(event.getHeaders()));
        if (response != null) {
            if (!String.class.equals(responseType)) {
                return JsonMapper.toJson(response);
            } else {
                return response.toString();
            }
        } else {
            return "";
        }
    }
}
