package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.ClientDTO;
import com.proyectoTC.Taller_17_TC.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class ClientConverterTest {

    private ClientConverter clientConverter;

    @BeforeEach
    public void setup() {
        clientConverter = new ClientConverter();
    }

    @Test
    public void testFromDTO() {
        ClientDTO dto = new ClientDTO();
        dto.setId(1L);
        dto.setName("Test");

        Client entity = clientConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

    @Test
    public void testFromEntity() {
        Client entity = new Client();
        entity.setId(1L);
        entity.setName("Test");

        ClientDTO dto = clientConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    public void testFromDTONull() {
        ClientDTO dto = null;
        Client entity = clientConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        Client entity = null;
        ClientDTO dto = clientConverter.fromEntity(entity);
        assertNull(dto);
    }


}
