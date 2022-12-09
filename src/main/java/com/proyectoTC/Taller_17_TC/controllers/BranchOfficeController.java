package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.BranchOfficeConverter;
import com.proyectoTC.Taller_17_TC.dtos.BranchOfficeDTO;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "branchOffice")
public class BranchOfficeController {

    @Autowired
    private BranchOfficeService branchOfficeService;

    @Autowired
    private BranchOfficeConverter branchOfficeConverter;

    @GetMapping
    public ResponseEntity<WrapperResponse<List<BranchOfficeDTO>>> getBranchOffices() {
        List<BranchOfficeDTO> branchOfficeDTOList = branchOfficeConverter.fromEntity(branchOfficeService.getAllBranchOffices());
        return new WrapperResponse<>(
                true,
                branchOfficeDTOList,
                "success"
        ).createResponse(HttpStatus.OK);
    }

}
