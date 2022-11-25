package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.StatePaymentDTO;
import com.proyectoTC.Taller_17_TC.models.StatePayment;

public class StatePaymentConverter extends AbstractConverter<StatePayment, StatePaymentDTO> {
    @Override
    public StatePayment fromDTO(StatePaymentDTO dto) {
        if (dto == null) return null;
        return StatePayment.builder()
                .id(dto.getId())
                .state(dto.getState())
                .build();
    }

    @Override
    public StatePaymentDTO fromEntity(StatePayment entity) {
        if (entity == null) return null;
        return StatePaymentDTO.builder()
                .id(entity.getId())
                .state(entity.getState())
                .build();
    }
}
