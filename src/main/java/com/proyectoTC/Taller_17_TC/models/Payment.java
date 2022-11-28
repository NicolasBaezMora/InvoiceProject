package com.proyectoTC.Taller_17_TC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdPayment")
    @SequenceGenerator(name = "seqIdPayment", sequenceName = "seq_id_payment")
    private Long id;

    @Column(name = "PAYMENT_DATE", nullable = false)
    private String paymentDate;

    @Column(name = "PAYMENT_TYPE", nullable = false, length = 50)
    private String paymentType;

    @Column(name = "PAYMENT_VALUE", nullable = false)
    private Double paymentValue;

    @ManyToOne(/*fetch = FetchType.EAGER*/)
    @JoinColumn(name = "ID_BRANCH_OFFICE", nullable = false)
    private BranchOffice branchOffice;

    @ManyToOne
    @JoinColumn(name = "ID_INVOICE")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "ID_STATE_PAYMENT", nullable = false)
    private StatePayment statePayment;

}
