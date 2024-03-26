package com.proyectoTC.Taller_17_TC.utils;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.InvoiceRepository;
import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import com.proyectoTC.Taller_17_TC.repositories.WalletRepository;
import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileProcessorImplTest {

    @Mock
    BranchOfficeRepository branchOfficeRepository;

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    WalletRepository walletRepository;

    @Mock
    FileLoaderImpl fileLoader;

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    FileProcessorImpl fileProcessor;

    private final String hash = "12345abcdef";

    private final String firstLine = "010000800038310000020221205401";

    private final String secondLine = "";

    private final MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "data".getBytes());

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrownValidateExceptionWithFirstLineBlank() throws IOException {
        BufferedReader mockBuffer = mock(BufferedReader.class);
        when(fileLoader.getData(file)).thenReturn(mockBuffer);
        when(mockBuffer.readLine()).thenReturn(null);

        assertThrows(ValidateServiceException.class, () -> {
            fileProcessor.processDataFile(file, hash);
        });
    }

    @Test
    void shouldThrownNotFoundExceptionByBranchOffice() throws IOException {
        BufferedReader mockBuffer = mock(BufferedReader.class);
        when(fileLoader.getData(file)).thenReturn(mockBuffer);
        when(mockBuffer.readLine()).thenReturn(firstLine);
        when(branchOfficeRepository.findById(1L)).thenReturn(null);

        assertThrows(NoDataFoundException.class, () -> {
            fileProcessor.processDataFile(file, hash);
        });
    }

    @Test
    void shouldThrownValidateExceptionWithInvalidHash() throws IOException {
        BufferedReader mockBuffer = mock(BufferedReader.class);
        when(fileLoader.getData(file)).thenReturn(mockBuffer);
        when(mockBuffer.readLine()).thenReturn(firstLine);
        when(branchOfficeRepository.findById(401L)).thenReturn(Optional.of(buildBranchOffice()));

        assertThrows(ValidateServiceException.class, () -> {
            fileProcessor.processDataFile(file, hash);
        });
    }

    @Test
    void testConvertDate() throws Exception {
        String inputDate = "20221231";
        String expectedOutput = "2022-12-31";

        Method convertDateMethod = FileProcessorImpl.class.getDeclaredMethod("convertDate", String.class);
        convertDateMethod.setAccessible(true);

        String actualOutput = (String) convertDateMethod.invoke(fileProcessor, inputDate);

        assertEquals(expectedOutput, actualOutput, "The converted date did not match the expected output");
    }

    @Test
    void testGenerateConsistentPayment() throws Exception {
        double valuePay = 100.0;
        String dateDetail = "20221231";
        int typePay = 1;
        Long idBranchOffice = 1L;
        Long idInvoice = 1L;

        Method generateConsistentPaymentMethod = FileProcessorImpl.class.getDeclaredMethod("generateConsistentPayment", double.class, String.class, int.class, Long.class, Long.class);
        generateConsistentPaymentMethod.setAccessible(true);

        when(jdbcTemplate.update(
                "{CALL PKG_PAYMENT_TRANSACTION.CREATE_PAYMENT(?, ?, ?, ?, ?, ?)}",
                100.0d,
                "2022-12-31",
                "EFECTIVO",
                1L,
                1L,
                1L
        )).thenReturn(1);

        generateConsistentPaymentMethod.invoke(fileProcessor, valuePay, dateDetail, typePay, idBranchOffice, idInvoice);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(
                "{CALL PKG_PAYMENT_TRANSACTION.CREATE_PAYMENT(?, ?, ?, ?, ?, ?)}",
                100.0d,
                "2022-12-31",
                "EFECTIVO",
                1L,
                1L,
                1L
        );
    }

    @Test
    void testGenerateInconsistentPayment() throws Exception {
        double valuePay = 100.0;
        String dateDetail = "20221231";
        int typePay = 1;
        Long idBranchOffice = 1L;
        Long idInvoice = 1L;

        Method generateInconsistentPaymentMethod = FileProcessorImpl.class.getDeclaredMethod("generateInconsistentPayment", double.class, String.class, int.class, Long.class, Long.class);
        generateInconsistentPaymentMethod.setAccessible(true);

        when(jdbcTemplate.update(
                "{CALL PKG_PAYMENT_TRANSACTION.CREATE_PAYMENT(?, ?, ?, ?, ?, ?)}",
                100.0d,
                "2022-12-31",
                "EFECTIVO",
                1L,
                1L,
                51L
        )).thenReturn(1);

        generateInconsistentPaymentMethod.invoke(fileProcessor, valuePay, dateDetail, typePay, idBranchOffice, idInvoice);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(
                "{CALL PKG_PAYMENT_TRANSACTION.CREATE_PAYMENT(?, ?, ?, ?, ?, ?)}",
                100.0d,
                "2022-12-31",
                "EFECTIVO",
                1L,
                1L,
                51L
        );
    }

    @Test
    void testUpdateWallet() throws Exception {
        double valuePay = 100.0;
        Invoice invoicePaid = new Invoice();
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setBalance(200.0);
        invoicePaid.setWallet(wallet);

        Method updateWalletMethod = FileProcessorImpl.class.getDeclaredMethod("updateWallet", Invoice.class, Double.class);
        updateWalletMethod.setAccessible(true);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        updateWalletMethod.invoke(fileProcessor, invoicePaid, valuePay);
        Mockito.verify(walletRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(walletRepository, Mockito.times(1)).save(Mockito.any(Wallet.class));
    }

    private BranchOffice buildBranchOffice() {
        return BranchOffice.builder()
                .id(401L)
                .hash("aaaa")
                .build();
    }

    private BranchOffice buildValidBranchOffice() {
        return BranchOffice.builder()
                .id(401L)
                .hash(hash)
                .build();
    }

    private ResponseFile buildResponseFile() {
        return ResponseFile.builder()
                .build();
    }
}
