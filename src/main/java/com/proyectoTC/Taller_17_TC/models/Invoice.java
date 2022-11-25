package com.proyectoTC.Taller_17_TC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INVOICE")
public class Invoice {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdInvoice")
    @SequenceGenerator(name = "seqIdInvoice", sequenceName = "seq_id_invoice")
    private Long id;

    @Column(name = "INVOICED_VALUE", nullable = false)
    private Double invoicedValue;

    @Column(name = "INVOICED_DATE", nullable = false)
    private String invoicedDate;

    @ManyToOne
    @JoinColumn(name = "ID_WALLET", nullable = false)
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "ID_STATE_INVOICE", nullable = false)
    private StateInvoice stateInvoice;

}
