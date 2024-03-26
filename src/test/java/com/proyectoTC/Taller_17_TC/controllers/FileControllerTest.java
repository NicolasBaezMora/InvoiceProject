package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import com.proyectoTC.Taller_17_TC.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileControllerTest {

    @Mock
    FileService fileService;

    @InjectMocks
    FileController fileController;

    private final String hash = "12345abcd";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUploadFile() {
        byte[] content = "content".getBytes();
        MultipartFile file = new MockMultipartFile("test", "text.pdf", "text/plain", content);
        when(fileService.loadFile(file, hash)).thenReturn(buildResponseFile());
        var response = fileController.uploadFile(file, hash);
        assertNotNull(response);
    }

    private ResponseFile buildResponseFile() {
        return ResponseFile.builder()
                .consistent(10L)
                .inconsistent(10L)
                .build();
    }

}
