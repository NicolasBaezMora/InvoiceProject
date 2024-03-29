package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.CommissionDTO;
import com.proyectoTC.Taller_17_TC.models.Commission;
import org.springframework.beans.factory.annotation.Autowired;

public class CommissionConverter extends AbstractConverter<Commission, CommissionDTO> {

    @Autowired
    private BranchOfficeConverter branchOfficeConverter;

    @Override
    public Commission fromDTO(CommissionDTO dto) {
        if (dto == null) return null;
        return Commission.builder()
                .id(dto.getId())
                .value(dto.getValue())
                .dateGen(dto.getDateGen())
                .dateInitialCalculation(dto.getDateInitialCalculation())
                .dateEndCalculation(dto.getDateEndCalculation())
                .branchOffice(branchOfficeConverter.fromDTO(dto.getBranchOfficeDTO()))
                .build();
    }

    @Override
    public CommissionDTO fromEntity(Commission entity) {
        if (entity == null) return null;
        return CommissionDTO.builder()
                .id(entity.getId())
                .value(entity.getValue())
                .dateGen(entity.getDateGen())
                .dateInitialCalculation(entity.getDateInitialCalculation())
                .dateEndCalculation(entity.getDateEndCalculation())
                .branchOfficeDTO(branchOfficeConverter.fromEntity(entity.getBranchOffice()))
                .build();
    }
}
