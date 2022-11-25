package com.proyectoTC.Taller_17_TC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BRANCH_OFFICE")
public class BranchOffice {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdBranchOffice")
    @SequenceGenerator(name = "seqIdBranchOffice", sequenceName = "seq_id_branch_office")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Column(name = "SANCTIONABLE", nullable = false, length = 1)
    private char sanctionable;

    @Column(name = "HASH", nullable = false, length = 50, unique = true)
    private String hash;

    @ManyToOne
    @JoinColumn(name = "ID_AGREEMENT_TYPE", nullable = false)
    private AgreementType agreementType;

}
