package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.AgreementTypeDTO;
import com.proyectoTC.Taller_17_TC.models.AgreementType;

public class AgreementTypeConverter extends AbstractConverter<AgreementType, AgreementTypeDTO> {

    @Override
    public AgreementType fromDTO(AgreementTypeDTO dto) {
        if (dto == null) return null;
        return AgreementType.builder()
                .id(dto.getId())
                .method(dto.getMethod())
                .build();
    }

    @Override
    public AgreementTypeDTO fromEntity(AgreementType entity) {
        if (entity == null) return null;
        return AgreementTypeDTO.builder()
                .id(entity.getId())
                .method(entity.getMethod())
                .build();
    }
}
