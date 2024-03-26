package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.CommissionConverter;
import com.proyectoTC.Taller_17_TC.dtos.CommissionDTO;
import com.proyectoTC.Taller_17_TC.models.Commission;
import com.proyectoTC.Taller_17_TC.response_models.ResponseData;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.CommissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommissionControllerTest {

    @Mock
    CommissionService commissionService;

    @Mock
    CommissionConverter commissionConverter;

    @InjectMocks
    CommissionController commissionController;

    private final String hash = "abcd123456";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetCommissions() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Commission> page = new PageImpl<>(buildCommissionsList(), pageable, 10);
        when(commissionService.getAllCommissionsByBranchOffice(pageable, hash)).thenReturn(page);

        ResponseEntity<WrapperResponse<ResponseData<CommissionDTO>>> response = commissionController.getCommissions(hash, 0);
        assertNotNull(response);
    }

    @Test
    void shouldGetCommissionsByDateRange() {
        when(commissionService.getAllCommissionsByBranchOfficeAndDateRange("", "", hash)).thenReturn(buildCommissionsList());
        ResponseEntity<WrapperResponse<List<CommissionDTO>>> response = commissionController.getCommissionsByDateRange(hash, "", "");
        assertNotNull(response);
    }

    @Test
    void shouldGenerateCommission() {
        ResponseEntity<WrapperResponse<Object>> response = commissionController.generateCommission(1L, "", "");
        assertNotNull(response);
    }


    private List<Commission> buildCommissionsList() {
        return Collections.singletonList(Commission.builder().build());
    }

}
