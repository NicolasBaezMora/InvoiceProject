package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchOfficeService {

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;


    public List<BranchOffice> getAllBranchOffices() {
        return branchOfficeRepository.findAll();
    }


}
