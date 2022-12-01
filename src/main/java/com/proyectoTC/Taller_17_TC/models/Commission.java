package com.proyectoTC.Taller_17_TC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMISSION")
public class Commission {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdCommission")
    @SequenceGenerator(name = "seqIdCommission", sequenceName = "seq_id_commission")
    private Long id;

    @Column(name = "VALUE", nullable = false)
    private Double value;

    @Column(name = "DATE_GEN", nullable = false, length = 10)
    private String dateGen;

    @Column(name = "DATE_END_CALCULATION", nullable = false, length = 10)
    private String dateEndCalculation;

    @Column(name = "DATE_INITIAL_CALCULATION", nullable = false, length = 10)
    private String dateInitialCalculation;

    @ManyToOne
    @JoinColumn(name = "ID_BRANCH_OFFICE", nullable = false)
    private BranchOffice branchOffice;

}
