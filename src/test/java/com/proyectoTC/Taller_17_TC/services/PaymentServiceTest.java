package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Payment;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    BranchOfficeRepository branchOfficeRepository;

    @InjectMocks
    PaymentService paymentService;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllConsistentPayments() {
        Pageable pageable = mock(Pageable.class);
        Page<Payment> page = mock(Page.class);
        BranchOffice branchOffice = mock(BranchOffice.class);
        when(branchOffice.getId()).thenReturn(1L);
        when(branchOfficeRepository.findByHash("hash")).thenReturn(Optional.of(branchOffice));
        when(paymentRepository.getConsistentPayments(pageable, branchOffice.getId())).thenReturn(page);

        Page<Payment> result = paymentService.getAllConsistentPayments(pageable, "hash");

        assertNotNull(result);
    }

    @Test
    void shouldThrowNoDataFoundException() {
        Pageable pageable = mock(Pageable.class);
        when(branchOfficeRepository.findByHash("hash")).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> paymentService.getAllConsistentPayments(pageable, "hash"));
    }

    @Test
    void shouldGetAllInconsistentPayments() {
        Pageable pageable = mock(Pageable.class);
        Page<Payment> page = mock(Page.class);
        BranchOffice branchOffice = mock(BranchOffice.class);
        when(branchOffice.getId()).thenReturn(1L);
        when(branchOfficeRepository.findByHash("hash")).thenReturn(Optional.of(branchOffice));
        when(paymentRepository.getInconsistentPayments(pageable, branchOffice.getId())).thenReturn(page);

        Page<Payment> result = paymentService.getAllInconsistentPayments(pageable, "hash");

        assertNotNull(result);
    }

    @Test
    void shouldThrowNoDataFoundException2() {
        Pageable pageable = mock(Pageable.class);
        when(branchOfficeRepository.findByHash("hash")).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> paymentService.getAllInconsistentPayments(pageable, "hash"));
    }

    @Test
    void shouldGetAllConsistentPaymentsByDateRange() {
        String hash = "hash";
        String dateStart = "2022-01-01";
        String dateEnd = "2022-12-31";
        List<Payment> payments = Collections.singletonList(mock(Payment.class));
        BranchOffice branchOffice = mock(BranchOffice.class);
        when(branchOffice.getId()).thenReturn(1L);
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.of(branchOffice));
        when(paymentRepository.getConsistentPaymentsByDateRange(branchOffice.getId(), dateStart, dateEnd)).thenReturn(payments);

        List<Payment> result = paymentService.getAllConsistentPaymentsByDateRange(hash, dateStart, dateEnd);

        assertNotNull(result);
    }

    @Test
    void shouldThrowNoDataFoundException3() {
        String hash = "hash";
        String dateStart = "2022-01-01";
        String dateEnd = "2022-12-31";
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> paymentService.getAllConsistentPaymentsByDateRange(hash, dateStart, dateEnd));
    }

    @Test
    void shouldGetAllInconsistentPaymentsByDateRange() {
        String hash = "hash";
        String dateStart = "2022-01-01";
        String dateEnd = "2022-12-31";
        List<Payment> payments = Collections.singletonList(mock(Payment.class));
        BranchOffice branchOffice = mock(BranchOffice.class);
        when(branchOffice.getId()).thenReturn(1L);
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.of(branchOffice));
        when(paymentRepository.getInconsistentPaymentsByDateRange(branchOffice.getId(), dateStart, dateEnd)).thenReturn(payments);

        List<Payment> result = paymentService.getAllInconsistentPaymentsByDateRange(hash, dateStart, dateEnd);

        assertNotNull(result);
    }

    @Test
    void shouldThrowNoDataFoundException4() {
        String hash = "hash";
        String dateStart = "2022-01-01";
        String dateEnd = "2022-12-31";
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> paymentService.getAllInconsistentPaymentsByDateRange(hash, dateStart, dateEnd));
    }

}
