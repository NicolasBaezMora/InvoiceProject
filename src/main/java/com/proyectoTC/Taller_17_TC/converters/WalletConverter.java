package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.WalletDTO;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;

public class WalletConverter extends AbstractConverter<Wallet, WalletDTO> {

    @Autowired
    private ClientConverter clientConverter;

    @Override
    public Wallet fromDTO(WalletDTO dto) {
        if (dto == null) return null;
        return Wallet.builder()
                .id(dto.getId())
                .balance(dto.getBalance())
                .client(clientConverter.fromDTO(dto.getClientDTO()))
                .build();
    }

    @Override
    public WalletDTO fromEntity(Wallet entity) {
        if (entity == null) return null;
        return WalletDTO.builder()
                .id(entity.getId())
                .balance(entity.getBalance())
                .clientDTO(clientConverter.fromEntity(entity.getClient()))
                .build();
    }
}
