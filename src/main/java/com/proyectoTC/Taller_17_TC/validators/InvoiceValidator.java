package com.proyectoTC.Taller_17_TC.validators;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.StateInvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceValidator {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private StateInvoiceRepository stateInvoiceRepository;

    public void saveInvoice(Invoice invoice) {
        if (invoice.getInvoicedDate() == null)
            throw new ValidateServiceException("La fecha de facturación es requerida");
        if (invoice.getInvoicedValue() == null)
            throw new ValidateServiceException("El valor de facturación es requerido");
        if (invoice.getWallet() == null)
            throw new ValidateServiceException("No se registro billetera para la factura");
        if (invoice.getStateInvoice() == null)
            throw new ValidateServiceException("No se registro estadop de factura para la factura");

        // Validations with the database *********************************************

        walletRepository.findById(invoice.getWallet().getId())
                .orElseThrow(() -> new NoDataFoundException("Billetera inexistente"));
        stateInvoiceRepository.findById(invoice.getStateInvoice().getId())
                .orElseThrow(() -> new NoDataFoundException("Estado de factura inexistente"));
    }

}

