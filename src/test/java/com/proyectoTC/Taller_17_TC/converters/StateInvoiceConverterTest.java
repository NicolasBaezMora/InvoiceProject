package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.StateInvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class StateInvoiceConverterTest {

    private StateInvoiceConverter stateInvoiceConverter;

    @BeforeEach
    public void setup() {
        stateInvoiceConverter = new StateInvoiceConverter();
    }

    @Test
    public void testFromDTO() {
        StateInvoiceDTO dto = new StateInvoiceDTO();
        dto.setId(1L);
        dto.setState("Test");

        StateInvoice entity = stateInvoiceConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getState(), entity.getState());
    }

    @Test
    public void testFromEntity() {
        StateInvoice entity = new StateInvoice();
        entity.setId(1L);
        entity.setState("Test");

        StateInvoiceDTO dto = stateInvoiceConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getState(), dto.getState());
    }

    @Test
    public void testFromDTONull() {
        StateInvoiceDTO dto = null;
        StateInvoice entity = stateInvoiceConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        StateInvoice entity = null;
        StateInvoiceDTO dto = stateInvoiceConverter.fromEntity(entity);
        assertNull(dto);
    }


}
