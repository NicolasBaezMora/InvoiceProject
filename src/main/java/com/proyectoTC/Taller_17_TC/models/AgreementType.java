package com.proyectoTC.Taller_17_TC.models;


import lombok.*;

import javax.persistence.*;

@Table(name = "AGREEMENT_TYPE")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementType {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdAgreementType")
    @SequenceGenerator(name = "seqIdAgreementType", sequenceName = "seq_id_agreement_type")
    private Long id;

    @Column(name = "METHOD_PAY", nullable = false, length = 50)
    private String method;

}
