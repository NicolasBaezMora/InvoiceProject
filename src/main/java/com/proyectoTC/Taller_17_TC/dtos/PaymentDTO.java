package com.proyectoTC.Taller_17_TC.dtos;

import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.StatePayment;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private String paymentDate;
    private String paymentType;
    private Double paymentValue;
    private BranchOffice branchOffice;
    private Invoice invoice;
    private StatePayment statePayment;
}
