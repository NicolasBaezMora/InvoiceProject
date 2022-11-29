package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.PaymentConverter;
import com.proyectoTC.Taller_17_TC.dtos.PaymentDTO;
import com.proyectoTC.Taller_17_TC.models.Payment;
import com.proyectoTC.Taller_17_TC.response_models.ResponseData;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentConverter paymentConverter;

    @GetMapping(value = "/consistent")
    public ResponseEntity<WrapperResponse<ResponseData<PaymentDTO>>> getConsistentPayments(
            @RequestParam(value = "hash") String hash,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> paymentPage = paymentService.getAllConsistentPayments(pageable, hash);

        ResponseData<PaymentDTO> responseData = new ResponseData<>();

        responseData.setTotalPages(paymentPage.getTotalPages());
        responseData.setTotal(paymentPage.getTotalElements());
        responseData.setData(paymentConverter.fromEntity(paymentPage.getContent()));
        responseData.setCurrentPage(paymentPage.getNumber());

        return new WrapperResponse<>(
                true,
                responseData,
                "success"
        ).createResponse(HttpStatus.OK);
    }

    @GetMapping(value = "/inconsistent")
    public ResponseEntity<WrapperResponse<ResponseData<PaymentDTO>>> getInconsistentPayments(
            @RequestParam(value = "hash") String hash,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> paymentPage = paymentService.getAllInconsistentPayments(pageable, hash);

        ResponseData<PaymentDTO> responseData = new ResponseData<>();

        responseData.setTotalPages(paymentPage.getTotalPages());
        responseData.setTotal(paymentPage.getTotalElements());
        responseData.setData(paymentConverter.fromEntity(paymentPage.getContent()));
        responseData.setCurrentPage(paymentPage.getNumber());

        return new WrapperResponse<>(
                true,
                responseData,
                "success"
        ).createResponse(HttpStatus.OK);
    }

}
