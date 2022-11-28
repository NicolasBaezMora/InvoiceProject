package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Client;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.ClientRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void updateBalanceWithSave(Invoice invoiceSaved) {
        Wallet walletFound = walletRepository.findById(invoiceSaved.getWallet().getId())
                .orElseThrow(() -> new NoDataFoundException("No se encontro billetera con el id:" + invoiceSaved.getWallet().getId()));
        walletFound.setBalance(walletFound.getBalance() - invoiceSaved.getInvoicedValue());
        walletRepository.save(walletFound);
    }

    @Transactional
    public void updateBalanceWithUpdate(
            Double newInvoicedValue,
            Double oldInvoicedValue,
            Long idWallet) {
        Wallet walletFound = walletRepository.findById(idWallet)
                .orElseThrow(() -> new NoDataFoundException("No se encontro billetera con el id:" + idWallet));
        walletFound.setBalance((walletFound.getBalance() + oldInvoicedValue) - newInvoicedValue);
        walletRepository.save(walletFound);
    }

    public Wallet getWallet(String email, String hash) {
        Client client =  clientRepository.findByEmail(email)
                .orElseThrow(() -> new NoDataFoundException("No se encontro cliente"));

        if (!client.getHash().equals(hash)) throw new ValidateServiceException("El hash del cliente no es valido");

        return walletRepository.findByClient(client)
                .orElseThrow(() -> new NoDataFoundException("No se encontro billetera ligado al cliente"));
    }


}
