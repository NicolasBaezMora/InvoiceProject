package com.proyectoTC.Taller_17_TC.utils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class PaymentTypeTest {


    @Test
    void testGetTypePayWithValidId() {
        int id = 1;
        String expectedType = "EFECTIVO";

        String actualType = PaymentType.getTypePay(id);

        assertEquals(expectedType, actualType, "The type did not match the expected output");
    }

    @Test
    void testGetTypePayWithInvalidId() {
        int id = 100;
        String expectedType = "Otro";

        String actualType = PaymentType.getTypePay(id);

        assertEquals(expectedType, actualType, "The type did not match the expected output");
    }

}
