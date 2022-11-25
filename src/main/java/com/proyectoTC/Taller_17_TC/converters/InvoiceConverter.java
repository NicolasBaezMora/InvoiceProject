package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.Invoice;

public class InvoiceConverter extends AbstractConverter<Invoice, InvoiceDTO> {
    @Override
    public Invoice fromDTO(InvoiceDTO dto) {
        if (dto == null) return null;
        return Invoice.builder()
                .id(dto.getId())
                .invoicedValue(dto.getInvoicedValue())
                .invoicedDate(dto.getInvoicedDate())
                .wallet(dto.getWallet())
                .stateInvoice(dto.getStateInvoice())
                .build();
    }

    @Override
    public InvoiceDTO fromEntity(Invoice entity) {
        if (entity == null) return null;
        return InvoiceDTO.builder()
                .id(entity.getId())
                .invoicedValue(entity.getInvoicedValue())
                .invoicedDate(entity.getInvoicedDate())
                .wallet(entity.getWallet())
                .stateInvoice(entity.getStateInvoice())
                .build();
    }
}
