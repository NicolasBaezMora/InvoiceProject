package com.proyectoTC.Taller_17_TC.converters;

import com.proyectoTC.Taller_17_TC.dtos.AgreementTypeDTO;
import com.proyectoTC.Taller_17_TC.models.AgreementType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class AgreementTypeConverterTest {

    @InjectMocks
    private AgreementTypeConverter agreementTypeConverter;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEntityFromDto() {
        var entity = agreementTypeConverter.fromDTO(buildAgreementTypeDTO());
        assertNotNull(entity);
    }

    @Test
    void shouldReturnDtoFromEntity() {
        var dto = agreementTypeConverter.fromEntity(buildAgreementType());
        assertNotNull(dto);
    }

    @Test
    void shouldReturnNullInEntityFromDto() {
        assertNull(agreementTypeConverter.fromDTO((AgreementTypeDTO) null));
    }

    @Test
    void shouldReturnNullInDtoFromEntity() {
        assertNull(agreementTypeConverter.fromEntity((AgreementType) null));
    }

    @Test
    void shouldReturnEntityListFromDtoList() {
        List<AgreementTypeDTO> dtos = Arrays.asList(buildAgreementTypeDTO(), buildAgreementTypeDTO());
        List<AgreementType> entities = agreementTypeConverter.fromDTO(dtos);
        assertEquals(dtos.size(), entities.size());
    }

    @Test
    void shouldReturnDtoListFromEntityList() {
        List<AgreementType> entities = Arrays.asList(buildAgreementType(), buildAgreementType());
        List<AgreementTypeDTO> dtos = agreementTypeConverter.fromEntity(entities);
        assertEquals(entities.size(), dtos.size());
    }

    @Test
    void shouldReturnEmptyEntityListFromEmptyDtoList() {
        List<AgreementTypeDTO> dtos = Collections.emptyList();
        List<AgreementType> entities = agreementTypeConverter.fromDTO(dtos);
        assertTrue(entities.isEmpty());
    }

    @Test
    void shouldReturnEmptyDtoListFromEmptyEntityList() {
        List<AgreementType> entities = Collections.emptyList();
        List<AgreementTypeDTO> dtos = agreementTypeConverter.fromEntity(entities);
        assertTrue(dtos.isEmpty());
    }

    private AgreementTypeDTO buildAgreementTypeDTO() {
        return AgreementTypeDTO.builder()
                .id(1L)
                .method("")
                .build();
    }

    private AgreementType buildAgreementType() {
        return AgreementType.builder()
                .id(1L)
                .method("")
                .build();
    }

}
