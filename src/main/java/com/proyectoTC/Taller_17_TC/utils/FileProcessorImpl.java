package com.proyectoTC.Taller_17_TC.utils;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class FileProcessorImpl implements FileProcessor {


    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private FileLoaderImpl fileLoader;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseFile processDataFile(MultipartFile file, String hash) throws IOException {
        // Cargo en memoria los datos  archivo
        BufferedReader bufferData = fileLoader.getData(file);

        // **********************************************************************************************
        long cons = 0L;
        long incons = 0L;
        // Leo la primera linea del archivo para validar la entidad y el hash (extraer datos de la cabecera)
        String line = bufferData.readLine();
        if (line == null) throw new ValidateServiceException("Estructura del archivo invalida");

        long idBranchOffice = Long.parseLong(line.substring(2));

        BranchOffice branchOffice = branchOfficeRepository.findById(idBranchOffice)
                .orElseThrow(() -> new NoDataFoundException("No se encontro la entidad registrada en el archivo de información con id: " + idBranchOffice));
        if (!branchOffice.getHash().equals(hash)) throw new ValidateServiceException("El hash de la entidad '" + branchOffice.getName() + "' no es valido");
        // **********************************************************************************************

        // Empiezo a leer los datos del archivo de la segunda linea para abajo
        while ((line = bufferData.readLine()) != null) {
            if (line.length() > 0) {
                var arrayData = line.split(";");
                int type = Integer.parseInt(arrayData[0]);
                if (type == 2) {
                    // Extraigo los datos de la linea leida
                    String dateDetail = arrayData[1];
                    Long idInvoice = Long.parseLong(arrayData[2]);
                    double valuePay = Double.parseDouble(arrayData[3]);
                    int typePay = Integer.parseInt(arrayData[4]);
                    // **********************************************************************************************

                    // Reviso si existe la factura
                    Optional<Invoice> invoiceFound = invoiceRepository.findById(idInvoice);
                    if (invoiceFound.isEmpty()) {
                        generateInconsistentPayment(valuePay, dateDetail, typePay, idBranchOffice, null);

                        incons++;

                        continue;
                    }
                    // **********************************************************************************************

                    // Si existe la factura extraigo los datos de esta y proceso la información
                    Invoice invoiceToPay = invoiceFound.get();
                    if (invoiceToPay.getStateInvoice().getState().equals("PENDIENTE")) {
                        if (invoiceToPay.getInvoicedValue() == valuePay){

                            generateConsistentPayment(valuePay, dateDetail, typePay, idBranchOffice, idInvoice);

                            cons++;

                            invoiceToPay.setInvoicedValue(0D);
                            invoiceToPay.setStateInvoice(StateInvoice.builder().id(1L).build());
                            invoiceRepository.save(invoiceToPay);

                            updateWallet(invoiceToPay, valuePay);
                        } else {
                            generateInconsistentPayment(valuePay, dateDetail, typePay, idBranchOffice, idInvoice);

                            incons++;

                        }
                    } else if (invoiceToPay.getStateInvoice().getState().equals("PAGADO")) {
                        generateConsistentPayment(valuePay, dateDetail, typePay, idBranchOffice, idInvoice);

                        cons++;

                        updateWallet(invoiceToPay, valuePay);
                    }
                    // **********************************************************************************************

                }
            }
        }
        bufferData.close();
        return new ResponseFile(cons, incons);

    }


    // **************************************** METODO JDBC TRANSACCIONAL ****************************************

    private void updateWallet(Invoice invoicePaid, Double valuePay) {
        Wallet walletFound = walletRepository.findById(invoicePaid.getWallet().getId())
                .orElseThrow(() -> new ValidateServiceException("Billetera no encontrada"));
        walletFound.setBalance(walletFound.getBalance() + valuePay);
        walletRepository.save(walletFound);
    }
    private void generateInconsistentPayment(
            double valuePay,
            String dateDetail,
            int typePay,
            Long idBranchOffice,
            Long idInvoice
    ) {
        jdbcTemplate.update(
                "{CALL PKG_PAYMENT_TRANSACTION.CREATE_PAYMENT(?, ?, ?, ?, ?, ?)}",
                valuePay,
                convertDate(dateDetail),
                PaymentType.getTypePay(typePay),
                idBranchOffice,
                idInvoice,
                51L
        );

    }

    private void generateConsistentPayment(
            double valuePay,
            String dateDetail,
            int typePay,
            Long idBranchOffice,
            Long idInvoice
    ){
        jdbcTemplate.update(
                "{CALL PKG_PAYMENT_TRANSACTION.CREATE_PAYMENT(?, ?, ?, ?, ?, ?)}",
                valuePay,
                convertDate(dateDetail),
                PaymentType.getTypePay(typePay),
                idBranchOffice,
                idInvoice,
                1L
        );
    }

    // ************************************************************************************************************************


    private String convertDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6);

        return year + "-" + month + "-" + day;
    }

}
