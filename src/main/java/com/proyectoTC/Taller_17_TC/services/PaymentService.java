package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.Payment;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    public Page<Payment> getAllConsistentPayments(Pageable pageable, String hash) {
        BranchOffice branchOffice = branchOfficeRepository.findByHash(hash)
                .orElseThrow(() -> new NoDataFoundException("No se encontro entidad registrada con el hash: " + hash));
        return paymentRepository.getConsistentPayments(pageable, branchOffice.getId());
    }

    public Page<Payment> getAllInconsistentPayments(Pageable pageable, String hash) {
        BranchOffice branchOffice = branchOfficeRepository.findByHash(hash)
                .orElseThrow(() -> new NoDataFoundException("No se encontro entidad registrada con el hash: " + hash));
        return paymentRepository.getInconsistentPayments(pageable, branchOffice.getId());
    }

}
