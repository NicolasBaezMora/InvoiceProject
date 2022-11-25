package com.proyectoTC.Taller_17_TC.dtos;

import com.proyectoTC.Taller_17_TC.models.Wallet;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;
    private String billingAddress;
    private String email;
    private String phone;
    private String hash;

}
