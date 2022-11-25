package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.GeneralServiceException;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
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

    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        try {
            InvoiceValidator.saveInvoice(invoice);
            if (invoice.getId() == null) return invoiceRepository.save(invoice);
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
