package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT * FROM APP_PAGOS.INVOICE I WHERE I.ID_STATE_INVOICE = 51", nativeQuery = true)
    Page<Invoice> getPendingInvoices(Pageable page);

    @Query(value = "SELECT * FROM APP_PAGOS.INVOICE I WHERE I.ID_STATE_INVOICE = 1", nativeQuery = true)
    Page<Invoice> getPaidInvoices(Pageable page);

}
