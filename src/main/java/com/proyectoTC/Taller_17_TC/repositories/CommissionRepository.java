package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Commission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    // ****************************************  IMPLEMENTACIÓN DEL METODO JPQL  ****************************************
    @Query(
            value = "SELECT C.id, C.value, C.value, C.dateEndCalculation, C.dateInitialCalculation, C.branchOffice FROM Commission C WHERE C.branchOffice = (:idBranchOffice)"
    )
    Page<Commission> getCommissions(Pageable pageable, @Param(value = "idBranchOffice") BranchOffice idBranchOffice);

    // ******************************************************************************************************************


}