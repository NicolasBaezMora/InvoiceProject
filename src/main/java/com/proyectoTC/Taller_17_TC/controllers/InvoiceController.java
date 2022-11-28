package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.InvoiceConverter;
import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.response_models.ResponseInvoice;
import com.proyectoTC.Taller_17_TC.services.InvoiceService;
import com.proyectoTC.Taller_17_TC.services.WalletService;
import com.proyectoTC.Taller_17_TC.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;

@RestController
@RequestMapping(path = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceConverter invoiceConverter;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private WalletService walletService;

    @GetMapping(value = "/pending")
    public ResponseEntity<WrapperResponse<ResponseInvoice>> getPendingInvoices(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Invoice> pageInvoice = invoiceService.getAllPendingInvoices(pageable);

        ResponseInvoice responseInvoice = ResponseInvoice.builder()
                .totalInvoices(pageInvoice.getTotalElements())
                .invoices(invoiceConverter.fromEntity(pageInvoice.getContent()))
                .currentPage(pageInvoice.getNumber())
                .totalPages(pageInvoice.getTotalPages())
                .build();

        return new WrapperResponse<>(
                true,
                responseInvoice,
                "success"
        ).createResponse(HttpStatus.OK);
    }

    @GetMapping(value = "/paid")
    public ResponseEntity<WrapperResponse<ResponseInvoice>> getPaidInvoices(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Invoice> pageInvoice = invoiceService.getAllPaidInvoices(pageable);

        ResponseInvoice responseInvoice = ResponseInvoice.builder()
                .totalInvoices(pageInvoice.getTotalElements())
                .invoices(invoiceConverter.fromEntity(pageInvoice.getContent()))
                .currentPage(pageInvoice.getNumber())
                .totalPages(pageInvoice.getTotalPages())
                .build();

        return new WrapperResponse<>(
                true,
                responseInvoice,
                "success"
        ).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<InvoiceDTO>> createInvoice(
            @RequestBody Invoice invoice
    ) {
        InvoiceDTO invoiceSaved =
                invoiceConverter.fromEntity(invoiceService.saveInvoice(invoice));

        // Actualizamos el balance de la cartera
        walletService.updateBalanceWithSave(invoiceConverter.fromDTO(invoiceSaved));

        return new WrapperResponse<>(
                true,
                invoiceSaved,
                "success"
        ).createResponse(HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<WrapperResponse<InvoiceDTO>> updateInvoice(
            @RequestBody Invoice invoice
    ) {
        Double invoicedValueOld = invoiceService.getInvoiceById(invoice.getId()).getInvoicedValue();
        InvoiceDTO invoiceUpdated =
                invoiceConverter.fromEntity(invoiceService.saveInvoice(invoice));

        walletService.updateBalanceWithUpdate(
                invoiceUpdated.getInvoicedValue(),
                invoicedValueOld,
                invoiceUpdated.getWalletDTO().getId()
        );

        return new WrapperResponse<>(
                true,
                invoiceUpdated,
                "success"
        ).createResponse(HttpStatus.OK);
    }

}
