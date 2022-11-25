package com.proyectoTC.Taller_17_TC.response_models;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseInvoice {
    private Long totalInvoices;
    private List<InvoiceDTO> invoices;
    private int totalPages;
    private int currentPage;
}
