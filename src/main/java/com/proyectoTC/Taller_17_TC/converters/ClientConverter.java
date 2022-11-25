package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.ClientDTO;
import com.proyectoTC.Taller_17_TC.models.Client;
import com.proyectoTC.Taller_17_TC.models.Wallet;

public class ClientConverter extends AbstractConverter<Client, ClientDTO> {
    @Override
    public Client fromDTO(ClientDTO dto) {
        if (dto == null) return null;
        return Client.builder()
                .id(dto.getId())
                .name(dto.getName())
                .billingAddress(dto.getBillingAddress())
                .email(dto.getEmail())
                //.wallet(Wallet.builder().id(dto.getWallet().getId()).balance(dto.getWallet().getBalance()).build())
                .hash(dto.getHash())
                .build();
    }

    @Override
    public ClientDTO fromEntity(Client entity) {
        if (entity == null) return null;
        return ClientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .billingAddress(entity.getBillingAddress())
                .email(entity.getEmail())
                //.wallet(Wallet.builder().id(entity.getWallet().getId()).balance(entity.getWallet().getBalance()).build())
                .hash(entity.getHash())
                .build();
    }
}
