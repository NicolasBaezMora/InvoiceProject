package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
