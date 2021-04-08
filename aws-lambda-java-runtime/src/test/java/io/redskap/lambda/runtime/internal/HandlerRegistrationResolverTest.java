package io.redskap.lambda.runtime.internal;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.redskap.lambda.runtime.RequestHandlerRegistration;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class HandlerRegistrationResolverTest {
    private HandlerRegistrationResolver underTest = new HandlerRegistrationResolver();

    @Test
    void testResolveShouldReturnNullWhenNullGiven() {
        // given
        RequestHandlerRegistration<String, String> stringHandlerRegistration = new RequestHandlerRegistration<>(new StringRequestHandler(), String.class, String.class);
        RequestHandlerRegistration<Object, Object> objectHandlerRegistration = new RequestHandlerRegistration<>(new ObjectRequestHandler(), Object.class, Object.class);
        Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations = asList(stringHandlerRegistration, objectHandlerRegistration);
        // when
        RequestHandlerRegistration<?, ?> result = underTest.resolve(null, handlerRegistrations);
        // then
        assertThat(result).isNull();
    }

    @Test
    void testResolveShouldReturnNullWhenEmptyStringGiven() {
        // given
        RequestHandlerRegistration<String, String> stringHandlerRegistration = new RequestHandlerRegistration<>(new StringRequestHandler(), String.class, String.class);
        RequestHandlerRegistration<Object, Object> objectHandlerRegistration = new RequestHandlerRegistration<>(new ObjectRequestHandler(), Object.class, Object.class);
        Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations = asList(stringHandlerRegistration, objectHandlerRegistration);
        // when
        RequestHandlerRegistration<?, ?> result = underTest.resolve("", handlerRegistrations);
        // then
        assertThat(result).isNull();
    }

    @Test
    void testResolveShouldReturnNullWhenOnlySimpleClassNameGiven() {
        // given
        RequestHandlerRegistration<String, String> stringHandlerRegistration = new RequestHandlerRegistration<>(new StringRequestHandler(), String.class, String.class);
        RequestHandlerRegistration<Object, Object> objectHandlerRegistration = new RequestHandlerRegistration<>(new ObjectRequestHandler(), Object.class, Object.class);
        Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations = asList(stringHandlerRegistration, objectHandlerRegistration);
        // when
        RequestHandlerRegistration<?, ?> result = underTest.resolve("HandlerRegistrationResolverTest$ObjectRequestHandler", handlerRegistrations);
        // then
        assertThat(result).isNull();
    }

    @Test
    void testResolveShouldReturnProperRegistrationWithOnlyFullyQualifiedClassName() {
        // given
        RequestHandlerRegistration<String, String> stringHandlerRegistration = new RequestHandlerRegistration<>(new StringRequestHandler(), String.class, String.class);
        RequestHandlerRegistration<Object, Object> objectHandlerRegistration = new RequestHandlerRegistration<>(new ObjectRequestHandler(), Object.class, Object.class);
        Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations = asList(stringHandlerRegistration, objectHandlerRegistration);
        // when
        RequestHandlerRegistration<?, ?> result = underTest.resolve("io.redskap.lambda.runtime.internal.HandlerRegistrationResolverTest$ObjectRequestHandler", handlerRegistrations);
        // then
        assertThat(result).isEqualTo(objectHandlerRegistration);
    }

    @Test
    void testResolveShouldReturnProperRegistrationWithOnlyFullyQualifiedClassNameAndHandlerMethod() {
        // given
        RequestHandlerRegistration<String, String> stringHandlerRegistration = new RequestHandlerRegistration<>(new StringRequestHandler(), String.class, String.class);
        RequestHandlerRegistration<Object, Object> objectHandlerRegistration = new RequestHandlerRegistration<>(new ObjectRequestHandler(), Object.class, Object.class);
        Collection<RequestHandlerRegistration<?, ?>> handlerRegistrations = asList(stringHandlerRegistration, objectHandlerRegistration);
        // when
        RequestHandlerRegistration<?, ?> result = underTest.resolve("io.redskap.lambda.runtime.internal.HandlerRegistrationResolverTest$ObjectRequestHandler::handleRequest", handlerRegistrations);
        // then
        assertThat(result).isEqualTo(objectHandlerRegistration);
    }

    private static class StringRequestHandler implements RequestHandler<String, String> {
        @Override
        public String handleRequest(String input, Context context) {
            return null;
        }
    }

    private static class ObjectRequestHandler implements RequestHandler<Object, Object> {
        @Override
        public Object handleRequest(Object input, Context context) {
            return null;
        }
    }
}