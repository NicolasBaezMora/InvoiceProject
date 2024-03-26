package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.StatePaymentDTO;
import com.proyectoTC.Taller_17_TC.models.StatePayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class StatePaymentConverterTest {

    private StatePaymentConverter statePaymentConverter;

    @BeforeEach
    public void setup() {
        statePaymentConverter = new StatePaymentConverter();
    }

    @Test
    public void testFromDTO() {
        StatePaymentDTO dto = new StatePaymentDTO();
        dto.setId(1L);
        dto.setState("Test");

        StatePayment entity = statePaymentConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getState(), entity.getState());
    }

    @Test
    public void testFromEntity() {
        StatePayment entity = new StatePayment();
        entity.setId(1L);
        entity.setState("Test");

        StatePaymentDTO dto = statePaymentConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getState(), dto.getState());
    }

    @Test
    public void testFromDTONull() {
        StatePaymentDTO dto = null;
        StatePayment entity = statePaymentConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        StatePayment entity = null;
        StatePaymentDTO dto = statePaymentConverter.fromEntity(entity);
        assertNull(dto);
    }

}
