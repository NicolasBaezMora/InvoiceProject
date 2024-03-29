package com.proyectoTC.Taller_17_TC.controllers;


import com.proyectoTC.Taller_17_TC.converters.CommissionConverter;
import com.proyectoTC.Taller_17_TC.dtos.CommissionDTO;
import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.Commission;
import com.proyectoTC.Taller_17_TC.response_models.ResponseData;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/commission")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private CommissionConverter commissionConverter;

    @GetMapping
    public ResponseEntity<WrapperResponse<ResponseData<CommissionDTO>>> getCommissions(
            @RequestParam(value = "hash") String hash,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page
            //@RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Commission> commissionPage = commissionService.getAllCommissionsByBranchOffice(pageable, hash);

        ResponseData<CommissionDTO> responseData = new ResponseData<>();
        responseData.setData(commissionConverter.fromEntity(commissionPage.getContent()));
        responseData.setTotalPages(commissionPage.getTotalPages());
        responseData.setCurrentPage(commissionPage.getNumber());
        responseData.setTotal(commissionPage.getTotalElements());

        return new WrapperResponse<>(
                true,
                responseData,
                "success"
        ).createResponse(HttpStatus.OK);
    }

    @GetMapping(value = "/commissionsByDateRange")
    public ResponseEntity<WrapperResponse<List<CommissionDTO>>> getCommissionsByDateRange(
            @RequestParam(value = "hash") String hash,
            @RequestParam(value = "dateStart") String dateStart,
            @RequestParam(value = "dateEnd") String dateEnd
    ) {
        return new WrapperResponse<>(
                true,
                commissionConverter.fromEntity(commissionService.getAllCommissionsByBranchOfficeAndDateRange(dateStart, dateEnd, hash)),
                "success"
        ).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<Object>> generateCommission(
            @RequestParam("idBranchOffice") Long idBranchOffice,
            @RequestParam("dateInitial") String dateInitial,
            @RequestParam("dateEnd") String dateEnd
    ) {

        commissionService.generateCommissionManually(idBranchOffice, dateInitial, dateEnd);

        return new WrapperResponse<>(
            true,
                null,
                "success"
        ).createResponse(HttpStatus.OK);
    }

}
