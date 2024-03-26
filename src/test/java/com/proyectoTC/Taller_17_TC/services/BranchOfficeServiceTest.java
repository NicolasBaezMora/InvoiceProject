package com.proyectoTC.Taller_17_TC.services;


import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import com.proyectoTC.Taller_17_TC.repositories.BranchOfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BranchOfficeServiceTest {


    @Mock
    BranchOfficeRepository branchOfficeRepository;

    @InjectMocks
    BranchOfficeService branchOfficeService;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllBranchOffices() {
        when(branchOfficeRepository.findAll()).thenReturn(buildListBranchOffice());
        var branchOffices = branchOfficeService.getAllBranchOffices();
        assertNotNull(branchOffices);
    }

    private List<BranchOffice> buildListBranchOffice() {
        return Collections.singletonList(BranchOffice.builder().build());
    }


}
