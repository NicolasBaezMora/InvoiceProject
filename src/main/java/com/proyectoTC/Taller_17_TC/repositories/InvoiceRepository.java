package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
