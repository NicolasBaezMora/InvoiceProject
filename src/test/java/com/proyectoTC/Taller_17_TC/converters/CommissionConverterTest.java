package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.CommissionDTO;
import com.proyectoTC.Taller_17_TC.models.Commission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommissionConverterTest {


    @InjectMocks
    private CommissionConverter commissionConverter;

    @Mock
    private BranchOfficeConverter branchOfficeConverter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFromDTO() {
        CommissionDTO dto = new CommissionDTO();
        dto.setId(1L);
        dto.setValue(100.0);

        when(branchOfficeConverter.fromDTO(dto.getBranchOfficeDTO())).thenReturn(null);

        Commission entity = commissionConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getValue(), entity.getValue());
        assertNull(entity.getBranchOffice());
    }

    @Test
    public void testFromEntity() {
        Commission entity = new Commission();
        entity.setId(1L);
        entity.setValue(100.0);

        when(branchOfficeConverter.fromEntity(entity.getBranchOffice())).thenReturn(null);

        CommissionDTO dto = commissionConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getValue(), dto.getValue());
        assertNull(dto.getBranchOfficeDTO());
    }

    @Test
    public void testFromDTONull() {
        CommissionDTO dto = null;
        Commission entity = commissionConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        Commission entity = null;
        CommissionDTO dto = commissionConverter.fromEntity(entity);
        assertNull(dto);
    }


}
