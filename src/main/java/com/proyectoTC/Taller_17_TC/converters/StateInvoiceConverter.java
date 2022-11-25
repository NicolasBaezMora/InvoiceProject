package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.StateInvoiceDTO;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;

public class StateInvoiceConverter extends AbstractConverter<StateInvoice, StateInvoiceDTO> {
    @Override
    public StateInvoice fromDTO(StateInvoiceDTO dto) {
        if (dto == null) return null;
        return StateInvoice.builder()
                .id(dto.getId())
                .state(dto.getState())
                .build();
    }

    @Override
    public StateInvoiceDTO fromEntity(StateInvoice entity) {
        if (entity == null) return null;
        return StateInvoiceDTO.builder()
                .id(entity.getId())
                .state(entity.getState())
                .build();
    }
}
