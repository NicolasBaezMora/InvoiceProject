package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.PaymentConverter;
import com.proyectoTC.Taller_17_TC.dtos.PaymentDTO;
import com.proyectoTC.Taller_17_TC.models.Payment;
import com.proyectoTC.Taller_17_TC.response_models.ResponseData;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PaymentControllerTest {


    @Mock
    PaymentService paymentService;

    @Mock
    PaymentConverter paymentConverter;

    private final String hash = "123456abcde";

    private final String dateStart = "2023/01/01";

    private final String dateEnd = "2023/11/01";

    @InjectMocks
    PaymentController paymentController;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldGetConsistentPayments() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Payment> page = new PageImpl<>(buildPaymentsList(), pageable, 10);
        when(paymentService.getAllConsistentPayments(pageable, hash)).thenReturn(page);

        ResponseEntity<WrapperResponse<ResponseData<PaymentDTO>>> response = paymentController.getConsistentPayments(hash, 0);
        assertNotNull(response);
    }


    @Test
    void shouldGetInconsistentPayments() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Payment> page = new PageImpl<>(buildPaymentsList(), pageable, 10);
        when(paymentService.getAllInconsistentPayments(pageable, hash)).thenReturn(page);

        ResponseEntity<WrapperResponse<ResponseData<PaymentDTO>>> response = paymentController.getInconsistentPayments(hash, 0);
        assertNotNull(response);
    }


    @Test
    void shouldGetConsistentPaymentsByDateRange() {
        when(paymentService.getAllConsistentPaymentsByDateRange(hash, dateStart, dateEnd)).thenReturn(buildPaymentsList());
        ResponseEntity<WrapperResponse<List<PaymentDTO>>> response = paymentController.getConsistentPaymentsByDateRange(hash, dateStart, dateEnd);
        assertNotNull(response);
    }

    @Test
    void shouldGetInconsistentPaymentsByDateRange() {
        when(paymentService.getAllInconsistentPaymentsByDateRange(hash, dateStart, dateEnd)).thenReturn(buildPaymentsList());
        ResponseEntity<WrapperResponse<List<PaymentDTO>>> response = paymentController.getInconsistentPaymentsByDateRange(hash, dateStart, dateEnd);
        assertNotNull(response);
    }

    private List<Payment> buildPaymentsList() {
        return Collections.singletonList(Payment.builder().build());
    }

}
