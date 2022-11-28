package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceConverter extends AbstractConverter<Invoice, InvoiceDTO> {

    @Autowired
    private WalletConverter walletConverter;

    @Override
    public Invoice fromDTO(InvoiceDTO dto) {
        if (dto == null) return null;
        return Invoice.builder()
                .id(dto.getId())
                .invoicedValue(dto.getInvoicedValue())
                .invoicedDate(dto.getInvoicedDate())
                .wallet(walletConverter.fromDTO(dto.getWalletDTO()))
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
                .walletDTO(walletConverter.fromEntity(entity.getWallet()))
                .stateInvoice(entity.getStateInvoice())
                .build();
    }
}
