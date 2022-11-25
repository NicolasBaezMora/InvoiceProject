package com.proyectoTC.Taller_17_TC.controllers;


import com.proyectoTC.Taller_17_TC.dtos.CommissionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/commissions")
public class CommissionController {

    @GetMapping
    public ResponseEntity<List<CommissionDTO>> getCommissions(
            @RequestParam(value = "hash") String hash,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        return null;
    }

}
