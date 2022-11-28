package com.proyectoTC.Taller_17_TC.services;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.proyectoTC.Taller_17_TC.exceptions.GeneralServiceException;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import com.proyectoTC.Taller_17_TC.validators.InvoiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private InvoiceValidator invoiceValidator;

    public Page<Invoice> getAllPendingInvoices(Pageable pageable) {
        return invoiceRepository.getPendingInvoices(pageable);
    }

    public Page<Invoice> getAllPaidInvoices(Pageable pageable) {
        return invoiceRepository.getPaidInvoices(pageable);
    }
    public Invoice getInvoiceById(Long idInvoice) {
        return invoiceRepository.findById(idInvoice)
                .orElseThrow(() -> new NoDataFoundException("No se encontro Factura con el id: " + idInvoice));
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        try {
            if (invoice.getId() == null) {
                Wallet wallet = walletRepository.findById(invoice.getWallet().getId())
                        .orElseThrow(() -> new NoDataFoundException("No se encontro billetera asociada la factura"));
                Double balanceWallet = wallet.getBalance();

                if (balanceWallet > 0) {
                    double diff = Math.abs(balanceWallet - invoice.getInvoicedValue());
                    if (balanceWallet >= invoice.getInvoicedValue()) {
                        // Actualizamos el balance de la cartera
                        wallet.setBalance(diff);
                        walletRepository.save(wallet);

                        invoice.setInvoicedValue(0D);
                        invoice.setStateInvoice(StateInvoice.builder().id(1L).build());
                    }
                    if (balanceWallet < invoice.getInvoicedValue()) {
                        // Actualizamos el balance de la cartera
                        wallet.setBalance(0D);
                        walletRepository.save(wallet);

                        invoice.setInvoicedValue(diff);
                    }

                }

                invoiceValidator.saveInvoice(invoice);
                return invoiceRepository.save(invoice);
            }
            Invoice invoiceToUpdate = invoiceRepository.findById(invoice.getId())
                    .orElseThrow(() -> new NoDataFoundException("No se encontro Factura con el id: " + invoice.getId()));

            invoiceToUpdate.setInvoicedValue(invoice.getInvoicedValue());
            invoiceToUpdate.setInvoicedDate(invoice.getInvoicedDate());

            return invoiceRepository.save(invoiceToUpdate);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GeneralServiceException(e);
        }
    }

}
