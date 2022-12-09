package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.StateInvoiceConverter;
import com.proyectoTC.Taller_17_TC.dtos.StateInvoiceDTO;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.StateInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "stateInvoice")
public class StateInvoiceController {

    @Autowired
    private StateInvoiceConverter stateInvoiceConverter;

    @Autowired
    private StateInvoiceService stateInvoiceService;

    @GetMapping
    public ResponseEntity<WrapperResponse<List<StateInvoiceDTO>>> getStateInvoices() {
        List<StateInvoiceDTO> stateInvoiceDTOList = stateInvoiceConverter.fromEntity(stateInvoiceService.getAllStateInvoice());
        return new WrapperResponse<>(
                true,
                stateInvoiceDTOList,
                "success"
        ).createResponse(HttpStatus.OK);
    }

}
