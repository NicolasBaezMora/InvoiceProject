package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(
            value = "SELECT ID, INVOICED_VALUE, INVOICED_DATE, ID_WALLET, ID_STATE_INVOICE FROM INVOICE I WHERE I.ID_STATE_INVOICE = 51 ORDER BY I.ID",
            countQuery = "SELECT COUNT(1) FROM INVOICE I WHERE I.ID_STATE_INVOICE = 51",
            nativeQuery = true
    )
    Page<Invoice> getPendingInvoices(Pageable page);

    @Query(
            value = "SELECT ID, INVOICED_VALUE, INVOICED_DATE, ID_WALLET, ID_STATE_INVOICE FROM INVOICE I WHERE I.ID_STATE_INVOICE = 1 ORDER BY I.ID",
            countQuery = "SELECT COUNT(1) FROM INVOICE I WHERE I.ID_STATE_INVOICE = 1",
            nativeQuery = true
    )
    Page<Invoice> getPaidInvoices(Pageable page);

}