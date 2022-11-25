package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.PaymentDTO;
import com.proyectoTC.Taller_17_TC.models.Payment;

public class PaymentConverter extends AbstractConverter<Payment, PaymentDTO> {
    @Override
    public Payment fromDTO(PaymentDTO dto) {
        if (dto == null) return null;
        return Payment.builder()
                .id(dto.getId())
                .paymentDate(dto.getPaymentDate())
                .paymentType(dto.getPaymentType())
                .statePayment(dto.getStatePayment())
                .paymentValue(dto.getPaymentValue())
                .invoice(dto.getInvoice())
                .branchOffice(dto.getBranchOffice())
                .build();
    }

    @Override
    public PaymentDTO fromEntity(Payment entity) {
        if (entity == null) return null;
        return PaymentDTO.builder()
                .id(entity.getId())
                .paymentDate(entity.getPaymentDate())
                .paymentType(entity.getPaymentType())
                .statePayment(entity.getStatePayment())
                .paymentValue(entity.getPaymentValue())
                .invoice(entity.getInvoice())
                .branchOffice(entity.getBranchOffice())
                .build();
    }
}
