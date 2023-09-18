package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.InvoiceConverter;
import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.response_models.ResponseData;
import com.proyectoTC.Taller_17_TC.services.InvoiceService;
import com.proyectoTC.Taller_17_TC.services.WalletService;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<WrapperResponse<ResponseData<InvoiceDTO>>> getPendingInvoices(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page
            //@RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Invoice> pageInvoice = invoiceService.getAllPendingInvoices(pageable);

        System.out.println(pageInvoice);

        ResponseData<InvoiceDTO> responseData = new ResponseData<>();
        responseData.setTotal(pageInvoice.getTotalElements());
        responseData.setData(invoiceConverter.fromEntity(pageInvoice.getContent()));
        responseData.setCurrentPage(pageInvoice.getNumber());
        responseData.setTotalPages(pageInvoice.getTotalPages());

        return new WrapperResponse<>(
                true,
                responseData,
                "success"
        ).createResponse(HttpStatus.OK);
    }

    @GetMapping(value = "/paid")
    public ResponseEntity<WrapperResponse<ResponseData<InvoiceDTO>>> getPaidInvoices(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page
            //@RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Invoice> pageInvoice = invoiceService.getAllPaidInvoices(pageable);

        ResponseData<InvoiceDTO> responseData = new ResponseData<>();
        responseData.setTotal(pageInvoice.getTotalElements());
        responseData.setData(invoiceConverter.fromEntity(pageInvoice.getContent()));
        responseData.setCurrentPage(pageInvoice.getNumber());
        responseData.setTotalPages(pageInvoice.getTotalPages());

        return new WrapperResponse<>(
                true,
                responseData,
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

}
