package io.redskap.lambda.runtime;

import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RequestHandlerRegistration<I, O> {
    private final RequestHandler<I, O> handler;
    private final Class<I> requestType;
    private final Class<O> responseType;

    public RequestHandlerRegistration(RequestHandler<I, O> handler, Class<I> requestType, Class<O> responseType) {
        this.handler = handler;
        this.requestType = requestType;
        this.responseType = responseType;
    }

    public RequestHandler<I, O> getHandler() {
        return handler;
    }

    public Class<I> getRequestType() {
        return requestType;
    }

    public Class<O> getResponseType() {
        return responseType;
    }
}
