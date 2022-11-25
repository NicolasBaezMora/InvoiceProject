package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.BranchOfficeDTO;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;

public class BranchOfficeConverter extends AbstractConverter<BranchOffice, BranchOfficeDTO> {
    @Override
    public BranchOffice fromDTO(BranchOfficeDTO dto) {
        if (dto == null) return null;
        return BranchOffice.builder()
                .id(dto.getId())
                .name(dto.getName())
                .agreementType(dto.getAgreementType())
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
                .agreementType(entity.getAgreementType())
                .hash(entity.getHash())
                .sanctionable(entity.getSanctionable())
                .build();
    }
}
