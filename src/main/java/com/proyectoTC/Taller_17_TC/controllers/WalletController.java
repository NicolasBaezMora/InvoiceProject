package com.proyectoTC.Taller_17_TC.controllers;


import com.proyectoTC.Taller_17_TC.converters.ClientConverter;
import com.proyectoTC.Taller_17_TC.converters.WalletConverter;
import com.proyectoTC.Taller_17_TC.dtos.ClientDTO;
import com.proyectoTC.Taller_17_TC.dtos.WalletDTO;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.services.WalletService;
import com.proyectoTC.Taller_17_TC.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private WalletConverter walletConverter;

    @GetMapping
    public ResponseEntity<WrapperResponse<WalletDTO>> getBalance(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "hash") String hash
    ) {
        WalletDTO walletFound = walletConverter.fromEntity(walletService.getWallet(email, hash));
        return new WrapperResponse<>(
                true,
                walletFound,
                "success"
        ).createResponse(HttpStatus.OK);
    }


}
