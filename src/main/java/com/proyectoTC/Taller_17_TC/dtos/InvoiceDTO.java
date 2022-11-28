package com.proyectoTC.Taller_17_TC.dtos;

import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Long id;
    private Double invoicedValue;
    private String invoicedDate;
    private WalletDTO walletDTO;
    private StateInvoice stateInvoice;
}
