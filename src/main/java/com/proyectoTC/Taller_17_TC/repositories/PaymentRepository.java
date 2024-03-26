package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.Payment;
import com.proyectoTC.Taller_17_TC.models.StatePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(
            value = "SELECT ID, PAYMENT_DATE, PAYMENT_TYPE, PAYMENT_VALUE, ID_BRANCH_OFFICE, ID_INVOICE, ID_STATE_PAYMENT FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 1 ORDER BY P.ID",
            countQuery = "SELECT COUNT(1) FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 1",
            nativeQuery = true
    )
    Page<Payment> getConsistentPayments(Pageable pageable, @Param(value = "idBranchOffice") Long idBranchOffice);

    @Query(
            value = "SELECT ID, PAYMENT_DATE, PAYMENT_TYPE, PAYMENT_VALUE, ID_BRANCH_OFFICE, ID_INVOICE, ID_STATE_PAYMENT FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 51 ORDER BY P.ID",
            countQuery = "SELECT COUNT(1) FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 51",
            nativeQuery = true
    )
    Page<Payment> getInconsistentPayments(Pageable pageable, @Param(value = "idBranchOffice") Long idBranchOffice);

    @Query(
            value = "SELECT ID, PAYMENT_DATE, PAYMENT_TYPE, PAYMENT_VALUE, ID_BRANCH_OFFICE, ID_INVOICE, ID_STATE_PAYMENT FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 1 AND P.PAYMENT_DATE BETWEEN :dateStart AND :dateEnd ORDER BY P.PAYMENT_DATE",
            nativeQuery = true
    )
    List<Payment> getConsistentPaymentsByDateRange(@Param(value = "idBranchOffice") Long idBranchOffice, @Param(value = "dateStart") String dateStart, @Param(value = "dateEnd") String dateEnd);

    @Query(
            value = "SELECT ID, PAYMENT_DATE, PAYMENT_TYPE, PAYMENT_VALUE, ID_BRANCH_OFFICE, ID_INVOICE, ID_STATE_PAYMENT FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 51 AND P.PAYMENT_DATE BETWEEN :dateStart AND :dateEnd ORDER BY P.PAYMENT_DATE",
            nativeQuery = true
    )
    List<Payment> getInconsistentPaymentsByDateRange(@Param(value = "idBranchOffice") Long idBranchOffice, @Param(value = "dateStart") String dateStart, @Param(value = "dateEnd") String dateEnd);


    @Query(
            value = "SELECT COUNT(1) FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 1",
            nativeQuery = true
    )
    Long getAmountConsistentPayments(@Param(value = "idBranchOffice") Long idBranchOffice);

    @Query(
            value = "SELECT COUNT(1) FROM PAYMENT P WHERE (P.ID_BRANCH_OFFICE = :idBranchOffice) AND P.ID_STATE_PAYMENT = 51",
            nativeQuery = true
    )
    Long getAmountInconsistentPayments(@Param(value = "idBranchOffice") Long idBranchOffice);

}