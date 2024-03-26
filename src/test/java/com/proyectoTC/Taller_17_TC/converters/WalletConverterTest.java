package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.WalletDTO;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WalletConverterTest {

    @InjectMocks
    private WalletConverter walletConverter;

    @Mock
    private ClientConverter clientConverter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFromDTO() {
        WalletDTO dto = new WalletDTO();
        dto.setId(1L);
        dto.setBalance(100.0);

        when(clientConverter.fromDTO(dto.getClientDTO())).thenReturn(null);

        Wallet entity = walletConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getBalance(), entity.getBalance());
        assertNull(entity.getClient());
    }

    @Test
    public void testFromEntity() {
        Wallet entity = new Wallet();
        entity.setId(1L);
        entity.setBalance(100.0);

        when(clientConverter.fromEntity(entity.getClient())).thenReturn(null);

        WalletDTO dto = walletConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getBalance(), dto.getBalance());
        assertNull(dto.getClientDTO());
    }

    @Test
    public void testFromDTONull() {
        WalletDTO dto = null;
        Wallet entity = walletConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        Wallet entity = null;
        WalletDTO dto = walletConverter.fromEntity(entity);
        assertNull(dto);
    }

}
