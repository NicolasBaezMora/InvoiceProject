package com.proyectoTC.Taller_17_TC.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wallet")
public class WalletController {

    @GetMapping
    public ResponseEntity<Double> getBalance(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "hash") String hash
    ) {
        return null;
    }


}
