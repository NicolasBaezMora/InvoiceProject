package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.models.Commission;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import com.proyectoTC.Taller_17_TC.repositories.CommissionRepository;
import com.proyectoTC.Taller_17_TC.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommissionServiceTest {


    @Mock
    CommissionRepository commissionRepository;

    @Mock
    BranchOfficeRepository branchOfficeRepository;

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    CommissionService commissionService;

    private final String hash = "12345abcde";

    private final Pageable page = PageRequest.of(0, 10);


    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllCommissionsByBranchOffice() {
        BranchOffice bo = BranchOffice.builder().id(1L).build();
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.of(bo));
        when(commissionRepository.getCommissions(page, bo)).thenReturn(buildPageCommission());
        Page<Commission> commissions = commissionService.getAllCommissionsByBranchOffice(page, hash);
        assertNotNull(commissions);
    }

    @Test
    void shouldThrowNoDataFoundException() {
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> commissionService.getAllCommissionsByBranchOffice(page, hash));
    }

    @Test
    void shouldThrowNoDataFoundExceptionInDateRange() {
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> commissionService.getAllCommissionsByBranchOfficeAndDateRange("", "", hash));
    }

    @Test
    void shouldGetAllCommissionsByBranchOfficeAndDateRange() {
        BranchOffice bo = BranchOffice.builder().id(1L).build();
        when(branchOfficeRepository.findByHash(hash)).thenReturn(Optional.of(bo));
        when(commissionRepository.getCommissionsByDateRange("", "", bo.getId())).thenReturn(buildCommissions());
        List<Commission> commissions = commissionService.getAllCommissionsByBranchOfficeAndDateRange("", "", hash);
        assertNotNull(commissions);
    }

    @Test
    void shouldGenerateCommissionManually() {
        when(paymentRepository.getAmountConsistentPayments(1L)).thenReturn(5L);
        when(paymentRepository.getAmountInconsistentPayments(1L)).thenReturn(5L);
        doNothing().when(commissionRepository).generateCommission(1L, 5L, 5L, "", "");
        commissionService.generateCommissionManually(1L, "", "");
    }

    private List<Commission> buildCommissions() {
        return Collections.singletonList(Commission.builder().build());
    }

    private Page<Commission> buildPageCommission() {
        return new PageImpl<>(buildCommissions(), page, 10);
    }

}
