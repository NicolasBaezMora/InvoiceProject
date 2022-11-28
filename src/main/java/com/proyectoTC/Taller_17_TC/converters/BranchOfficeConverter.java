package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.AgreementTypeDTO;
import com.proyectoTC.Taller_17_TC.dtos.BranchOfficeDTO;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import org.springframework.beans.factory.annotation.Autowired;

public class BranchOfficeConverter extends AbstractConverter<BranchOffice, BranchOfficeDTO> {

    @Autowired
    private AgreementTypeConverter agreementTypeConverter;

    @Override
    public BranchOffice fromDTO(BranchOfficeDTO dto) {
        if (dto == null) return null;
        return BranchOffice.builder()
                .id(dto.getId())
                .name(dto.getName())
                .agreementType(agreementTypeConverter.fromDTO(dto.getAgreementTypeDTO()))
                .hash(dto.getHash())
                .sanctionable(dto.getSanctionable())
                .build();
    }

    @Override
    public BranchOfficeDTO fromEntity(BranchOffice entity) {
        if (entity == null) return null;
        return BranchOfficeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .agreementTypeDTO(agreementTypeConverter.fromEntity(entity.getAgreementType()))
                .hash(entity.getHash())
                .sanctionable(entity.getSanctionable())
                .build();
    }
}
