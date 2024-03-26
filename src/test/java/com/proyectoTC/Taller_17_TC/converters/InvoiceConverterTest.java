package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.Invoice;
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
public class InvoiceConverterTest {

    @InjectMocks
    private InvoiceConverter invoiceConverter;

    @Mock
    private WalletConverter walletConverter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFromDTO() {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(1L);
        dto.setInvoicedValue(100.0);

        when(walletConverter.fromDTO(dto.getWalletDTO())).thenReturn(null);

        Invoice entity = invoiceConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getInvoicedValue(), entity.getInvoicedValue());
        assertNull(entity.getWallet());
    }

    @Test
    public void testFromEntity() {
        Invoice entity = new Invoice();
        entity.setId(1L);
        entity.setInvoicedValue(100.0);

        when(walletConverter.fromEntity(entity.getWallet())).thenReturn(null);

        InvoiceDTO dto = invoiceConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getInvoicedValue(), dto.getInvoicedValue());
        assertNull(dto.getWalletDTO());
    }

    @Test
    public void testFromDTONull() {
        InvoiceDTO dto = null;
        Invoice entity = invoiceConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        Invoice entity = null;
        InvoiceDTO dto = invoiceConverter.fromEntity(entity);
        assertNull(dto);
    }


}
