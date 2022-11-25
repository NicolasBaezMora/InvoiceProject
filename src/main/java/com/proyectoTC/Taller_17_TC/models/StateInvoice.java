package com.proyectoTC.Taller_17_TC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STATE_INVOICE")
public class StateInvoice {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdStateInvoice")
    @SequenceGenerator(name = "seqIdStateInvoice", sequenceName = "seq_id_state_invoice")
    private Long id;

    @Column(name = "STATE", nullable = false, length = 50)
    private String state;

}
