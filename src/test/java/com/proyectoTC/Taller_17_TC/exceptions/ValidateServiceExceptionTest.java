package com.proyectoTC.Taller_17_TC.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class ValidateServiceExceptionTest {

    @Test
    void testEmptyConstructor() {
        Exception exception = new ValidateServiceException();
        assertNull(exception.getMessage());
    }

    @Test
    void testMessageConstructor() {
        String message = "Test message";
        Exception exception = new ValidateServiceException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testMessageCauseConstructor() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");
        Exception exception = new ValidateServiceException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testCauseConstructor() {
        Throwable cause = new RuntimeException("Test cause");
        Exception exception = new ValidateServiceException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testFullConstructor() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");
        Exception exception = new ValidateServiceException(message, cause, true, true);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}