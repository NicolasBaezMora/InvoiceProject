package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;



}
