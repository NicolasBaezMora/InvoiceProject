package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Commission;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    public Page<Commission> getAllCommissionsByBranchOffice(Pageable pageable, String hash) {
        BranchOffice branchOffice = branchOfficeRepository.findByHash(hash)
                .orElseThrow(() -> new NoDataFoundException("No se encontro entidad con el hash: " + hash));
        return commissionRepository.getCommissions(pageable, branchOffice);
    }

}
