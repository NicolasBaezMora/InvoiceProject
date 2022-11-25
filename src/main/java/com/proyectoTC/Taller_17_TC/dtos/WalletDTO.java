package com.proyectoTC.Taller_17_TC.dtos;

import com.proyectoTC.Taller_17_TC.models.Client;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {

    private Long id;
    private Double balance;
    private Client client;

}
