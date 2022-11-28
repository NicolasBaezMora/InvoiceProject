package com.proyectoTC.Taller_17_TC.response_models;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInvoice<T> {
    private Long totalInvoices;
    private List<T> data;
    private int totalPages;
    private int currentPage;
}
