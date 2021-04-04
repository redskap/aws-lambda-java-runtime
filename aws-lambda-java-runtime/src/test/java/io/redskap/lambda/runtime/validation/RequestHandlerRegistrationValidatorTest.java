package io.redskap.lambda.runtime.validation;

import io.redskap.lambda.runtime.RequestHandlerRegistration;
import io.redskap.lambda.runtime.exception.InitializationException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestHandlerRegistrationValidatorTest {
    private final RequestHandlerRegistrationValidator underTest = new RequestHandlerRegistrationValidator();

    @Test
    void testValidateShouldThrowExceptionWhenNullGiven() {
        // given
        RequestHandlerRegistration<String, String> registration = null;
        // when & then
        assertThrows(InitializationException.class, () -> {
            underTest.validate(registration);
        });
    }

    @Test
    void testValidateShouldThrowExceptionWhenUnsupportedRequestClassGiven() {
        // given
        RequestHandlerRegistration<Object, Object> registration = new RequestHandlerRegistration<>((input, context) -> input, Object.class, Object.class);
        // when & then
        assertThrows(InitializationException.class, () -> {
            underTest.validate(registration);
        });
    }

    @Test
    void testValidateShouldThrowExceptionWhenUnsupportedResponseClassGiven() {
        // given
        RequestHandlerRegistration<String, Object> registration = new RequestHandlerRegistration<>((input, context) -> input, String.class, Object.class);
        // when & then
        assertThrows(InitializationException.class, () -> {
            underTest.validate(registration);
        });
    }

    @Test
    void testValidateShouldWorkWhenFrameworkSupportedClassGiven() {
        // given
        RequestHandlerRegistration<String, String> registration = new RequestHandlerRegistration<>((input, context) -> input, String.class, String.class);
        // when & then
        assertDoesNotThrow(() -> underTest.validate(registration));
    }


    @Test
    void testValidateShouldWorkWhenExtendedSupportedClassGiven() {
        // given
        RequestHandlerRegistrationValidator validator = new RequestHandlerRegistrationValidator(Collections.singleton(TestPojo.class));
        RequestHandlerRegistration<TestPojo, TestPojo> registration = new RequestHandlerRegistration<>((input, context) -> input, TestPojo.class, TestPojo.class);
        // when & then
        assertDoesNotThrow(() -> validator.validate(registration));
    }

    private static class TestPojo {
    }
}