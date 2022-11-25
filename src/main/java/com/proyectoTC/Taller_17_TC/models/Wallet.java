package com.proyectoTC.Taller_17_TC.models;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WALLET")
public class Wallet {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqIdStateWallet")
    @SequenceGenerator(name = "seqIdStateWallet", sequenceName = "seq_id_wallet")
    private Long id;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENT", nullable = false, unique = true)
    private Client client;

}
