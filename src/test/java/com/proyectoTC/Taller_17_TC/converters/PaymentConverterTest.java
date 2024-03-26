package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.PaymentDTO;
import com.proyectoTC.Taller_17_TC.models.Payment;
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
public class PaymentConverterTest {

    @InjectMocks
    private PaymentConverter paymentConverter;

    @Mock
    private StatePaymentConverter statePaymentConverter;

    @Mock
    private InvoiceConverter invoiceConverter;

    @Mock
    private BranchOfficeConverter branchOfficeConverter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFromDTO() {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(1L);
        dto.setPaymentValue(100.0);

        when(statePaymentConverter.fromDTO(dto.getStatePaymentDTO())).thenReturn(null);
        when(invoiceConverter.fromDTO(dto.getInvoiceDTO())).thenReturn(null);
        when(branchOfficeConverter.fromDTO(dto.getBranchOfficeDTO())).thenReturn(null);

        Payment entity = paymentConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getPaymentValue(), entity.getPaymentValue());
        assertNull(entity.getStatePayment());
        assertNull(entity.getInvoice());
        assertNull(entity.getBranchOffice());
    }

    @Test
    public void testFromEntity() {
        Payment entity = new Payment();
        entity.setId(1L);
        entity.setPaymentValue(100.0);

        when(statePaymentConverter.fromEntity(entity.getStatePayment())).thenReturn(null);
        when(invoiceConverter.fromEntity(entity.getInvoice())).thenReturn(null);
        when(branchOfficeConverter.fromEntity(entity.getBranchOffice())).thenReturn(null);

        PaymentDTO dto = paymentConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getPaymentValue(), dto.getPaymentValue());
        assertNull(dto.getStatePaymentDTO());
        assertNull(dto.getInvoiceDTO());
        assertNull(dto.getBranchOfficeDTO());
    }

    @Test
    public void testFromDTONull() {
        PaymentDTO dto = null;
        Payment entity = paymentConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        Payment entity = null;
        PaymentDTO dto = paymentConverter.fromEntity(entity);
        assertNull(dto);
    }


}
