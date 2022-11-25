package com.proyectoTC.Taller_17_TC.dtos;

import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommissionDTO {

    private Long id;
    private Double value;
    private String dateGen;
    private String dateEndCalculation;
    private String dateInitialCalculation;
    private BranchOffice branchOffice;


}
