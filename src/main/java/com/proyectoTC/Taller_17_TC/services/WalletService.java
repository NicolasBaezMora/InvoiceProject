package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public void updateBalanceWithSave(InvoiceDTO invoiceSaved) {
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

}
