package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.repositories.StateInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateInvoiceService {

    @Autowired
    private StateInvoiceRepository stateInvoiceRepository;

    public List<StateInvoice> getAllStateInvoice() {
        return stateInvoiceRepository.findAll();
    }

}
