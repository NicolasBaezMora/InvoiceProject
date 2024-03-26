package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.InvoiceConverter;
import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.services.InvoiceService;
import com.proyectoTC.Taller_17_TC.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class InvoiceControllerTest {


    @Mock
    InvoiceService invoiceService;

    @Mock
    InvoiceConverter invoiceConverter;

    @Mock
    WalletService walletService;

    @InjectMocks
    InvoiceController invoiceController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldGetPendingInvoices() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Invoice> page = new PageImpl<>(buildInvoicesList(), pageable, 10);
        when(invoiceService.getAllPendingInvoices(pageable)).thenReturn(page);

        var response = invoiceController.getPendingInvoices(0);
        assertNotNull(response);

    }

    @Test
    void shouldGetPaidInvoices() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Invoice> page = new PageImpl<>(buildInvoicesList(), pageable, 10);
        when(invoiceService.getAllPaidInvoices(pageable)).thenReturn(page);

        var response = invoiceController.getPaidInvoices(0);
        assertNotNull(response);
    }

    @Test
    void shouldGetPendingInvoicesByDateRange() {
        when(invoiceService.getAllPendingInvoicesByDateRange(anyString(), anyString())).thenReturn(buildInvoicesList());
        var response = invoiceController.getPendingInvoicesByDateRange(anyString(), anyString());
        assertNotNull(response);
    }

    @Test
    void shouldGetPaidInvoicesByDateRange() {
        when(invoiceService.getAllPaidInvoicesByDateRange(anyString(), anyString())).thenReturn(buildInvoicesList());
        var response = invoiceController.getPaidInvoicesByDateRange(anyString(), anyString());
        assertNotNull(response);
    }

    @Test
    void shouldSaveInvoice() {
        when(invoiceService.saveInvoice(buildInvoice())).thenReturn(buildInvoice());
        var response = invoiceController.createInvoice(buildInvoice());
        assertNotNull(response);
    }


    private List<Invoice> buildInvoicesList() {
        return Collections.singletonList(Invoice.builder().build());
    }

    private Invoice buildInvoice() {
        return Invoice.builder().build();
    }

}
