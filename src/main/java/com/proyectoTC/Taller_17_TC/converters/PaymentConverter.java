package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.PaymentDTO;
import com.proyectoTC.Taller_17_TC.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentConverter extends AbstractConverter<Payment, PaymentDTO> {

    @Autowired
    private StatePaymentConverter statePaymentConverter;

    @Autowired
    private InvoiceConverter invoiceConverter;

    @Autowired
    private BranchOfficeConverter branchOfficeConverter;

    @Override
    public Payment fromDTO(PaymentDTO dto) {
        if (dto == null) return null;
        return Payment.builder()
                .id(dto.getId())
                .paymentDate(dto.getPaymentDate())
                .paymentType(dto.getPaymentType())
                .statePayment(statePaymentConverter.fromDTO(dto.getStatePaymentDTO()))
                .paymentValue(dto.getPaymentValue())
                .invoice(invoiceConverter.fromDTO(dto.getInvoiceDTO()))
                .branchOffice(branchOfficeConverter.fromDTO(dto.getBranchOfficeDTO()))
                .build();
    }

    @Override
    public PaymentDTO fromEntity(Payment entity) {
        if (entity == null) return null;
        return PaymentDTO.builder()
                .id(entity.getId())
                .paymentDate(entity.getPaymentDate())
                .paymentType(entity.getPaymentType())
                .statePaymentDTO(statePaymentConverter.fromEntity(entity.getStatePayment()))
                .paymentValue(entity.getPaymentValue())
                .invoiceDTO(invoiceConverter.fromEntity(entity.getInvoice()))
                .branchOfficeDTO(branchOfficeConverter.fromEntity(entity.getBranchOffice()))
                .build();
    }
}
