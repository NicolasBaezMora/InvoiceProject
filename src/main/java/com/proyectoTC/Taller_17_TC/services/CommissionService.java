package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Commission;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.CommissionRepository;
import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Page<Commission> getAllCommissionsByBranchOffice(Pageable pageable, String hash) {
        BranchOffice branchOffice = branchOfficeRepository.findByHash(hash)
                .orElseThrow(() -> new NoDataFoundException("No se encontro entidad con el hash: " + hash));
        return commissionRepository.getCommissions(pageable, branchOffice);
    }

    public List<Commission> getAllCommissionsByBranchOfficeAndDateRange(String dateStart, String dateEnd, String hash) {
        BranchOffice branchOffice = branchOfficeRepository.findByHash(hash)
                .orElseThrow(() -> new NoDataFoundException("No se encontro entidad con el hash: " + hash));
        return commissionRepository.getCommissionsByDateRange(dateStart, dateEnd, branchOffice.getId());
    }

    @Transactional
    public void generateCommissionManually(Long idBranchOffice, String dateInitial, String dateEnd) {
        Long consistent = paymentRepository.getAmountConsistentPayments(idBranchOffice);
        Long inconsistent = paymentRepository.getAmountInconsistentPayments(idBranchOffice);
        commissionRepository.generateCommission(idBranchOffice, inconsistent, consistent, dateInitial, dateEnd);
    }

}
