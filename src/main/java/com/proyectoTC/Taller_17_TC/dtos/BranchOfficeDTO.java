package com.proyectoTC.Taller_17_TC.dtos;


import com.proyectoTC.Taller_17_TC.models.AgreementType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchOfficeDTO {

    private Long id;
    private String name;
    private char sanctionable;
    private String hash;
    private AgreementTypeDTO agreementTypeDTO;

}
