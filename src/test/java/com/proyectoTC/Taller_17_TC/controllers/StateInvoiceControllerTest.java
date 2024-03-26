package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.StateInvoiceConverter;
import com.proyectoTC.Taller_17_TC.dtos.StateInvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.StateInvoiceService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StateInvoiceControllerTest {


    @Mock
    StateInvoiceConverter stateInvoiceConverter;

    @Mock
    StateInvoiceService stateInvoiceService;

    @InjectMocks
    StateInvoiceController stateInvoiceController;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetStateInvoices() {
        when(stateInvoiceService.getAllStateInvoice()).thenReturn(buildResponse());
        ResponseEntity<WrapperResponse<List<StateInvoiceDTO>>> response = stateInvoiceController.getStateInvoices();
        assertNotNull(response);
    }

    private List<StateInvoice> buildResponse() {
        return Collections.singletonList(StateInvoice.builder().build());
    }


}
