package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.*;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import com.proyectoTC.Taller_17_TC.utils.PaymentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
@Slf4j
public class FileService {

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private WalletRepository walletRepository;


    @Transactional
    public ResponseFile loadFile(MultipartFile file, String hash) {
        long cons = 0L;
        long incons = 0L;
        try {
            Thread.sleep(1000); // LATENCIA SIMULADA!!!!!!!!!!!!!
            // Cargo en memoria los datos del archivo
            Reader readerData = new InputStreamReader(file.getInputStream());
            BufferedReader bufferData = new BufferedReader(readerData);
            // **********************************************************************************************

            // Leo la primera linea del archivo para validar la entidad y el hash (extraer datos de la cabecera)
            String line = bufferData.readLine();
            if (line == null) throw new ValidateServiceException("Estructura del archivo invalida");

            int amountInvoices = Integer.parseInt(line.substring(2, 7));
            double total = Double.parseDouble(line.substring(7, 16)) + Double.parseDouble(line.substring(16, 19)) / 1000;
            String dateHeader = line.substring(19, 27);
            long idBranchOffice = Long.parseLong(line.substring(27));

            BranchOffice branchOffice = branchOfficeRepository.findById(idBranchOffice)
                    .orElseThrow(() -> new NoDataFoundException("No se encontro la entidad registrada en el archivo de información con id: " + idBranchOffice));
            if (!branchOffice.getHash().equals(hash)) throw new ValidateServiceException("El hash de la entidad '" + branchOffice.getName() + "' no es valido");
            // **********************************************************************************************

            // TODO: REGISTRAR CABECERA

            // Empiezo a leer los datos del archivo de la segunda linea para abajo
            while ((line = bufferData.readLine()) != null) {
                if (line.length() > 0) {
                    int type = Integer.parseInt(line.substring(0, 2));
                    if (type == 2) {
                        // Extraigo los datos de la linea leida
                        String dateDetail = line.substring(2, 10);
                        Long idInvoice = Long.parseLong(line.substring(10, 15));
                        double valuePay = Double.parseDouble(line.substring(15, 22)) + Double.parseDouble(line.substring(22, 25)) / 1000;
                        int typePay = Integer.parseInt(line.substring(25));
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
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ResponseFile(cons, incons);
    }

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
        paymentRepository.savePayment(
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
        paymentRepository.savePayment(
                valuePay,
                convertDate(dateDetail),
                PaymentType.getTypePay(typePay),
                idBranchOffice,
                idInvoice,
                1L
        );
    }

    private String convertDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6);

        return year + "-" + month + "-" + day;
    }


}
