package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.repositories.StateInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StateInvoiceServiceTest {

    @Mock
    StateInvoiceRepository stateInvoiceRepository;

    @InjectMocks
    StateInvoiceService stateInvoiceService;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllStateInvoice() {
        List<StateInvoice> stateInvoices = Collections.singletonList(mock(StateInvoice.class));
        when(stateInvoiceRepository.findAll()).thenReturn(stateInvoices);

        List<StateInvoice> result = stateInvoiceService.getAllStateInvoice();

        assertNotNull(result);
    }

}
