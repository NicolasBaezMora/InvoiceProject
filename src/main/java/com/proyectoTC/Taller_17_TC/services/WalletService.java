package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.dtos.InvoiceDTO;
import com.proyectoTC.Taller_17_TC.dtos.WalletDTO;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Client;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.ClientRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import com.proyectoTC.Taller_17_TC.utils.WalletRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Wallet> getAllWallets(){
        return walletRepository.findAll();
    }

    // **************************************** METODO JDBC TRANSACCIONAL ****************************************
    @Transactional
    public void updateBalanceWithSave(Invoice invoiceSaved) {
        Wallet walletFound = walletRepository.findById(invoiceSaved.getWallet().getId())
                .orElseThrow(() -> new NoDataFoundException("No se encontro billetera con el id:" + invoiceSaved.getWallet().getId()));
        Double newBalance = walletFound.getBalance() - invoiceSaved.getInvoicedValue();
        jdbcTemplate.update("{CALL PKG_WALLET_TRANSACTION.UPDATE_BALANCE(?, ?)}", walletFound.getId(), newBalance);
    }
    // ************************************************************************************************************************

    public Wallet getWallet(String email, String hash) {
        Client client =  clientRepository.findByEmail(email)
                .orElseThrow(() -> new NoDataFoundException("No se encontro cliente"));

        if (!client.getHash().equals(hash)) throw new ValidateServiceException("El hash del cliente no es valido");


        String sql = "SELECT ID, BALANCE, ID_CLIENT FROM WALLET W WHERE W.ID_CLIENT = " + client.getId();
        return jdbcTemplate.query(sql, new WalletRowMapper(client)).stream().findFirst()
                .orElseThrow(() -> new NoDataFoundException("No se encontro billetera ligado al cliente"));

    }



}
