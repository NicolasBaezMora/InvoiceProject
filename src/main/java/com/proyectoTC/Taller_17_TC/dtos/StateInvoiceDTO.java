package com.proyectoTC.Taller_17_TC.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateInvoiceDTO {

    private Long id;
    private String state;

}
