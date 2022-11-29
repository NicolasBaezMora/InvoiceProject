package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Commission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    @Query(
            value = "SELECT * FROM COMMISSION C WHERE C.ID_BRANCH_OFFICE = :idBranchOffice",
            countQuery = "SELECT COUNT(*) FROM COMMISSION C WHERE C.ID_BRANCH_OFFICE = :idBranchOffice",
            nativeQuery = true
    )
    Page<Commission> getCommissions(Pageable pageable, @Param(value = "idBranchOffice") Long idBranchOffice);

}
