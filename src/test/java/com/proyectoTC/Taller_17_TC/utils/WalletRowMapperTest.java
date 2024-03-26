package com.proyectoTC.Taller_17_TC.utils;

import com.proyectoTC.Taller_17_TC.models.Client;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WalletRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        Client client = new Client();

        when(mockResultSet.getLong("ID")).thenReturn(1L);
        when(mockResultSet.getDouble("BALANCE")).thenReturn(100.0);

        WalletRowMapper walletRowMapper = new WalletRowMapper(client);

        Wallet wallet = walletRowMapper.mapRow(mockResultSet, 1);

        Mockito.verify(mockResultSet, Mockito.times(1)).getLong("ID");
        Mockito.verify(mockResultSet, Mockito.times(1)).getDouble("BALANCE");

        assertEquals(1L, wallet.getId());
        assertEquals(100.0, wallet.getBalance());
        assertEquals(client, wallet.getClient());
    }

}
