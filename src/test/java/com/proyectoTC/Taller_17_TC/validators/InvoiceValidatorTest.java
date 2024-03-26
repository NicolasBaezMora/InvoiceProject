package com.proyectoTC.Taller_17_TC.validators;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.StateInvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class InvoiceValidatorTest {


    @InjectMocks
    private InvoiceValidator invoiceValidator;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private StateInvoiceRepository stateInvoiceRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoicedDate(null);
        invoice.setInvoicedValue(null);
        invoice.setWallet(null);
        invoice.setStateInvoice(null);

        assertThrows(ValidateServiceException.class, () -> invoiceValidator.saveInvoice(invoice));

        invoice.setInvoicedDate("2022-01-01");
        invoice.setInvoicedValue(100.0);
        invoice.setWallet(new Wallet());
        invoice.setStateInvoice(new StateInvoice());

        when(walletRepository.findById(invoice.getWallet().getId())).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> invoiceValidator.saveInvoice(invoice));


    }

    @Test
    public void testSaveInvoiceInvoicedValueNull() {
        Invoice invoice = new Invoice();
        invoice.setInvoicedDate("");
        invoice.setInvoicedValue(null);

        assertThrows(ValidateServiceException.class, () -> invoiceValidator.saveInvoice(invoice));
    }

    @Test
    public void testSaveInvoiceStateInvoiceValueNull() {
        Invoice invoice = new Invoice();
        invoice.setInvoicedDate("");
        invoice.setInvoicedValue(1D);
        invoice.setWallet(null);

        assertThrows(ValidateServiceException.class, () -> invoiceValidator.saveInvoice(invoice));
    }

    @Test
    public void testSaveInvoiceWalletValueNull() {
        Invoice invoice = new Invoice();
        invoice.setInvoicedDate("");
        invoice.setInvoicedValue(1D);
        invoice.setWallet(Wallet.builder().build());
        invoice.setStateInvoice(null);

        assertThrows(ValidateServiceException.class, () -> invoiceValidator.saveInvoice(invoice));
    }

    @Test
    public void testFindStateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoicedDate("");
        invoice.setInvoicedValue(1D);
        invoice.setWallet(Wallet.builder().id(1L).build());
        invoice.setStateInvoice(StateInvoice.builder().id(1L).build());

        when(walletRepository.findById(invoice.getWallet().getId())).thenReturn(Optional.of(Wallet.builder().build()));
        when(stateInvoiceRepository.findById(invoice.getStateInvoice().getId())).thenReturn(Optional.empty());
        try {
            invoiceValidator.saveInvoice(invoice);
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Estado de factura inexistente");
        }
    }
}
