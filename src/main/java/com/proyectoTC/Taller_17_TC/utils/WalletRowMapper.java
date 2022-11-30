package com.proyectoTC.Taller_17_TC.utils;

import com.proyectoTC.Taller_17_TC.models.Client;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


@RequiredArgsConstructor
public class WalletRowMapper implements RowMapper<Wallet> {

    private final Client client;


    @Override
    public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Wallet.builder()
                .id(rs.getLong("ID"))
                .balance(rs.getDouble("BALANCE"))
                .client(client)
                .build();
    }
}
