package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.AgreementTypeDTO;
import com.proyectoTC.Taller_17_TC.dtos.BranchOfficeDTO;
import com.proyectoTC.Taller_17_TC.models.AgreementType;
import com.proyectoTC.Taller_17_TC.models.BranchOffice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BranchOfficeConverterTest {

    @InjectMocks
    private BranchOfficeConverter branchOfficeConverter;

    @Mock
    private AgreementTypeConverter agreementTypeConverter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFromDTO() {
        BranchOfficeDTO dto = new BranchOfficeDTO();
        dto.setId(1L);
        dto.setName("Test");

        when(agreementTypeConverter.fromDTO(dto.getAgreementTypeDTO())).thenReturn(null);

        BranchOffice entity = branchOfficeConverter.fromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertNull(entity.getAgreementType());
    }

    @Test
    public void testFromEntity() {
        BranchOffice entity = new BranchOffice();
        entity.setId(1L);
        entity.setName("Test");

        when(agreementTypeConverter.fromEntity(entity.getAgreementType())).thenReturn(null);

        BranchOfficeDTO dto = branchOfficeConverter.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertNull(dto.getAgreementTypeDTO());
    }

    @Test
    public void testFromDTONull() {
        BranchOfficeDTO dto = null;
        BranchOffice entity = branchOfficeConverter.fromDTO(dto);
        assertNull(entity);
    }

    @Test
    public void testFromEntityNull() {
        BranchOffice entity = null;
        BranchOfficeDTO dto = branchOfficeConverter.fromEntity(entity);
        assertNull(dto);
    }


    @Test
    void shouldReturnEntityListFromDtoList() {
        List<BranchOfficeDTO> dtos = Arrays.asList(buildBranchOfficeDTO(), buildBranchOfficeDTO());
        List<BranchOffice> entities = branchOfficeConverter.fromDTO(dtos);
        assertEquals(dtos.size(), entities.size());
    }

    @Test
    void shouldReturnDtoListFromEntityList() {
        List<BranchOffice> entities = Arrays.asList(buildBranchOffice(), buildBranchOffice());
        List<BranchOfficeDTO> dtos = branchOfficeConverter.fromEntity(entities);
        assertEquals(entities.size(), dtos.size());
    }

    @Test
    void shouldReturnEmptyEntityListFromEmptyDtoList() {
        List<BranchOfficeDTO> dtos = Collections.emptyList();
        List<BranchOffice> entities = branchOfficeConverter.fromDTO(dtos);
        assertTrue(entities.isEmpty());
    }

    @Test
    void shouldReturnEmptyDtoListFromEmptyEntityList() {
        List<BranchOffice> entities = Collections.emptyList();
        List<BranchOfficeDTO> dtos = branchOfficeConverter.fromEntity(entities);
        assertTrue(dtos.isEmpty());
    }

    private BranchOfficeDTO buildBranchOfficeDTO() {
        return BranchOfficeDTO.builder()
                .id(1L)
                .name("Test")
                .agreementTypeDTO(new AgreementTypeDTO())
                .hash("hash")
                .sanctionable('s')
                .build();
    }

    private BranchOffice buildBranchOffice() {
        return BranchOffice.builder()
                .id(1L)
                .name("Test")
                .agreementType(new AgreementType())
                .hash("hash")
                .sanctionable('s')
                .build();
    }

}
