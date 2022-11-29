package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(
            value = "SELECT * FROM (SELECT * FROM PAYMENT P WHERE P.ID_STATE_PAYMENT = 1) PS WHERE PS.ID_BRANCH_OFFICE = :idBranchOffice",
            countQuery = "SELECT COUNT(*) FROM (SELECT * FROM PAYMENT P WHERE P.ID_STATE_PAYMENT = 1) PS WHERE PS.ID_BRANCH_OFFICE = :idBranchOffice",
            nativeQuery = true
    )
    Page<Payment> getConsistentPayments(Pageable pageable, @Param(value = "idBranchOffice") Long idBranchOffice);

    @Query(
            value = "SELECT * FROM (SELECT * FROM PAYMENT P WHERE P.ID_STATE_PAYMENT = 51) PS WHERE PS.ID_BRANCH_OFFICE = :idBranchOffice",
            countQuery = "SELECT COUNT(*) FROM (SELECT * FROM PAYMENT P WHERE P.ID_STATE_PAYMENT = 51) PS WHERE PS.ID_BRANCH_OFFICE = :idBranchOffice",
            nativeQuery = true
    )
    Page<Payment> getInconsistentPayments(Pageable pageable, @Param(value = "idBranchOffice") Long idBranchOffice);
}
