package com.proyectoTC.Taller_17_TC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdClient")
    @SequenceGenerator(name = "seqIdClient", sequenceName = "seq_id_client")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 90)
    private String name;

    @Column(name = "BILLING_ADDRESS", nullable = false, length = 50)
    private String billingAddress;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "PHONE", nullable = false, length = 10)
    private String phone;

    @Column(name = "HASH", nullable = false, length = 50, unique = true)
    private String hash;

    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "client")
    //private Wallet wallet;

}
