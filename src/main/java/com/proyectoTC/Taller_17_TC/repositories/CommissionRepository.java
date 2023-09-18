package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Commission;
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

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    @Query(
            value = "SELECT ID, VALUE, DATE_GEN, DATE_END_CALCULATION, DATE_INITIAL_CALCULATION, ID_BRANCH_OFFICE FROM COMMISSION C WHERE C.ID_BRANCH_OFFICE = :idBranchOffice ORDER BY C.ID",
            countQuery = "SELECT COUNT(1) FROM COMMISSION C WHERE C.ID_BRANCH_OFFICE = :idBranchOffice",
            nativeQuery = true
    )
    Page<Commission> getCommissions(Pageable pageable, @Param(value = "idBranchOffice") BranchOffice idBranchOffice);

    @Modifying
    @Query(
            value = "{CALL PKG_COMMISSION_CALC.CALC_COMMISSION(:idBranchOffice, :consistent, :inconsistent, TO_DATE(:dateInitial, 'yyyy-MM-dd'), TO_DATE(:dateEnd, 'yyyy-MM-dd'))}",
            nativeQuery = true
    )
    void generateCommission(
            @Param(value = "idBranchOffice") Long idBranchOffice,
            @Param(value = "consistent") Long consistent,
            @Param(value = "inconsistent") Long inconsistent,
            @Param(value = "dateInitial") String dateInitial,
            @Param(value = "dateEnd") String dateEnd
    );


}