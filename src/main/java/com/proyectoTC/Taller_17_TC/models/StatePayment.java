package com.proyectoTC.Taller_17_TC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STATE_PAYMENT")
public class StatePayment {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdStatePayment")
    @SequenceGenerator(name = "seqIdStatePayment", sequenceName = "seq_id_state_payment")
    private Long id;

    @Column(name = "STATE", nullable = false, length = 50)
    private String state;

}
