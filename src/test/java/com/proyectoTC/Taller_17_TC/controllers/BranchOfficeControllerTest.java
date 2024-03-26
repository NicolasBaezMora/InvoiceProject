package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.converters.BranchOfficeConverter;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.services.BranchOfficeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BranchOfficeControllerTest {

    @Mock
    BranchOfficeService branchOfficeService;

    @Mock
    BranchOfficeConverter branchOfficeConverter;

    @InjectMocks
    BranchOfficeController controller;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetBranchOffices() {
        when(branchOfficeService.getAllBranchOffices()).thenReturn(buiBranchOfficeList());
        var responseEntity = controller.getBranchOffices();
        assertNotNull(responseEntity);
    }

    private List<BranchOffice> buiBranchOfficeList() {
        BranchOffice branchOffice = BranchOffice.builder().build();
        return Collections.singletonList(branchOffice);
    }


}
