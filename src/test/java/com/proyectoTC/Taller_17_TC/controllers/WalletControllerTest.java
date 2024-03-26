package com.proyectoTC.Taller_17_TC.controllers;


import com.proyectoTC.Taller_17_TC.converters.WalletConverter;
import com.proyectoTC.Taller_17_TC.dtos.WalletDTO;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WalletControllerTest {


    @Mock
    WalletService walletService;

    @Mock
    WalletConverter walletConverter;

    @InjectMocks
    WalletController walletController;


    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldGetAllWallets() {
        when(walletService.getAllWallets()).thenReturn(buildListWallets());
        ResponseEntity<WrapperResponse<List<WalletDTO>>> response = walletController.getAllWallets();
        assertNotNull(response);
    }

    @Test
    void shouldGetBalance() {
        when(walletService.getWallet("", "")).thenReturn(buildWallet());
        ResponseEntity<WrapperResponse<WalletDTO>> response = walletController.getBalance("", "");
        assertNotNull(response);
    }

    private List<Wallet> buildListWallets() {
        return Collections.singletonList(Wallet.builder().build());
    }

    private Wallet buildWallet() {
        return Wallet.builder().build();
    }

}
