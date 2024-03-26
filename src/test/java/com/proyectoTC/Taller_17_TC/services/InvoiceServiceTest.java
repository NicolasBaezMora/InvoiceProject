package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.GeneralServiceException;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import com.proyectoTC.Taller_17_TC.validators.InvoiceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class InvoiceServiceTest {

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    WalletRepository walletRepository;

    @Mock
    InvoiceValidator invoiceValidator;

    @InjectMocks
    InvoiceService invoiceService;


    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllPendingInvoices() {
        Pageable pageable = mock(Pageable.class);
        Page<Invoice> page = mock(Page.class);
        when(invoiceRepository.getPendingInvoices(pageable)).thenReturn(page);

        Page<Invoice> result = invoiceService.getAllPendingInvoices(pageable);

        assertNotNull(result);
    }

    @Test
    void shouldGetAllPaidInvoices() {
        Pageable pageable = mock(Pageable.class);
        Page<Invoice> page = mock(Page.class);
        when(invoiceRepository.getPaidInvoices(pageable)).thenReturn(page);

        Page<Invoice> result = invoiceService.getAllPaidInvoices(pageable);

        assertNotNull(result);
    }

    @Test
    void shouldGetAllPendingInvoicesByDateRange() {
        String dateStart = "2022-01-01";
        String dateEnd = "2022-12-31";
        List<Invoice> invoices = Collections.singletonList(mock(Invoice.class));
        when(invoiceRepository.getPendingInvoicesByDateRange(dateStart, dateEnd)).thenReturn(invoices);

        List<Invoice> result = invoiceService.getAllPendingInvoicesByDateRange(dateStart, dateEnd);

        assertNotNull(result);
    }

    @Test
    void shouldGetAllPaidInvoicesByDateRange() {
        String dateStart = "2022-01-01";
        String dateEnd = "2022-12-31";
        List<Invoice> invoices = Collections.singletonList(mock(Invoice.class));
        when(invoiceRepository.getPaidInvoicesByDateRange(dateStart, dateEnd)).thenReturn(invoices);

        List<Invoice> result = invoiceService.getAllPaidInvoicesByDateRange(dateStart, dateEnd);

        assertNotNull(result);
    }

    @Test
    void shouldSaveInvoiceNewInvoice() {
        Invoice invoice = mock(Invoice.class);
        Wallet wallet = mock(Wallet.class);

        when(invoice.getId()).thenReturn(null);
        when(invoice.getWallet()).thenReturn(wallet);
        when(wallet.getId()).thenReturn(1L);
        when(wallet.getBalance()).thenReturn(100.0);
        when(invoice.getInvoicedValue()).thenReturn(50.0);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        Invoice result = invoiceService.saveInvoice(invoice);

        assertNotNull(result);
        verify(walletRepository, times(1)).save(wallet);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void shouldSaveInvoiceUpdateInvoice() {
        Invoice invoice = mock(Invoice.class);

        when(invoice.getId()).thenReturn(1L);
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        Invoice result = invoiceService.saveInvoice(invoice);

        assertNotNull(result);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void shouldSaveInvoiceNoDataFoundException() {
        Invoice invoice = mock(Invoice.class);
        Wallet wallet = mock(Wallet.class);

        when(invoice.getId()).thenReturn(null);
        when(invoice.getWallet()).thenReturn(wallet);
        when(wallet.getId()).thenReturn(1L);
        when(walletRepository.findById(1L)).thenThrow(new NoDataFoundException("No se encontro billetera asociada la factura"));

        assertThrows(NoDataFoundException.class, () -> invoiceService.saveInvoice(invoice));
    }

    @Test
    void shouldSaveInvoiceValidateServiceException() {
        Invoice invoice = mock(Invoice.class);
        Wallet wallet = mock(Wallet.class);

        when(invoice.getId()).thenReturn(null);
        when(invoice.getWallet()).thenReturn(wallet);
        when(wallet.getId()).thenReturn(1L);
        when(wallet.getBalance()).thenReturn(100.0);
        when(invoice.getInvoicedValue()).thenReturn(50.0);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        doThrow(new ValidateServiceException("Error de validación")).when(invoiceValidator).saveInvoice(invoice);

        assertThrows(ValidateServiceException.class, () -> invoiceService.saveInvoice(invoice));
    }

    @Test
    void shouldSaveInvoiceGeneralServiceException() {
        Invoice invoice = mock(Invoice.class);
        Wallet wallet = mock(Wallet.class);

        when(invoice.getId()).thenReturn(null);
        when(invoice.getWallet()).thenReturn(wallet);
        when(wallet.getId()).thenReturn(1L);
        when(wallet.getBalance()).thenReturn(100.0);
        when(invoice.getInvoicedValue()).thenReturn(50.0);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        when(invoiceRepository.save(invoice)).thenThrow(new RuntimeException("Error inesperado"));

        assertThrows(GeneralServiceException.class, () -> invoiceService.saveInvoice(invoice));
    }


    @Test
    void testSaveInvoice_UpdateWalletBalance() {
        Invoice invoice = mock(Invoice.class);
        Wallet wallet = mock(Wallet.class);

        when(invoice.getId()).thenReturn(null);
        when(invoice.getWallet()).thenReturn(wallet);
        when(wallet.getId()).thenReturn(1L);
        when(wallet.getBalance()).thenReturn(100.0);
        when(invoice.getInvoicedValue()).thenReturn(150.0);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        invoiceService.saveInvoice(invoice);
        verify(walletRepository, times(1)).save(wallet);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void shouldSaveInvoiceNoDataFoundException2() {
        Invoice invoice = mock(Invoice.class);

        when(invoice.getId()).thenReturn(1L);
        when(invoiceRepository.findById(1L)).thenThrow(new NoDataFoundException("No se encontro Factura con el id: " + 1L));

        assertThrows(NoDataFoundException.class, () -> invoiceService.saveInvoice(invoice));
    }
}
