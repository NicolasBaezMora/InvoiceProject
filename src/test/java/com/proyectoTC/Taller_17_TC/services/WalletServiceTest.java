package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Client;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.ClientRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import com.proyectoTC.Taller_17_TC.utils.WalletRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class WalletServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    WalletService walletService;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllWallets() {
        List<Wallet> wallets = Collections.singletonList(mock(Wallet.class));
        when(walletRepository.findAll()).thenReturn(wallets);

        List<Wallet> result = walletService.getAllWallets();

        assertNotNull(result);
    }

    @Test
    void shouldUpdateBalanceWithSave() {
        Invoice invoice = mock(Invoice.class);
        Wallet wallet = mock(Wallet.class);

        when(invoice.getWallet()).thenReturn(wallet);
        when(wallet.getId()).thenReturn(1L);
        when(wallet.getBalance()).thenReturn(100.0);
        when(invoice.getInvoicedValue()).thenReturn(50.0);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));

        assertDoesNotThrow(() -> walletService.updateBalanceWithSave(invoice));
    }

    @Test
    void shouldThrowNoDataFoundException() {
        Invoice invoice = mock(Invoice.class);
        Wallet wallet = mock(Wallet.class);

        when(invoice.getWallet()).thenReturn(wallet);
        when(wallet.getId()).thenReturn(1L);
        when(walletRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> walletService.updateBalanceWithSave(invoice));
    }
    @Test
    void shouldGetWallet() {
        String email = "test@test.com";
        String hash = "hash";
        Client client = mock(Client.class);
        Wallet wallet = mock(Wallet.class);

        when(client.getHash()).thenReturn(hash);
        when(client.getId()).thenReturn(1L);
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));
        when(jdbcTemplate.query(anyString(), any(WalletRowMapper.class))).thenReturn(Collections.singletonList(wallet));

        Wallet result = walletService.getWallet(email, hash);

        assertNotNull(result);
    }

    @Test
    void shouldThrowNoDataFoundException2() {
        String email = "test@test.com";
        String hash = "hash";

        when(clientRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> walletService.getWallet(email, hash));
    }

    @Test
    void shouldThrowValidateServiceException() {
        String email = "test@test.com";
        String hash = "hash";
        Client client = mock(Client.class);

        when(client.getHash()).thenReturn("differentHash");
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));

        assertThrows(ValidateServiceException.class, () -> walletService.getWallet(email, hash));
    }

    @Test
    void shouldThrowNoDataFoundException3() {
        String email = "test@test.com";
        String hash = "hash";
        Client client = mock(Client.class);

        when(client.getHash()).thenReturn(hash);
        when(client.getId()).thenReturn(1L);
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));
        when(jdbcTemplate.query(anyString(), any(WalletRowMapper.class))).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> walletService.getWallet(email, hash));
    }

}
