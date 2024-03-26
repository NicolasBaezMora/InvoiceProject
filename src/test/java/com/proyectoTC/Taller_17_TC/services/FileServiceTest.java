package com.proyectoTC.Taller_17_TC.services;

import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import com.proyectoTC.Taller_17_TC.utils.FileProcessor;
import com.proyectoTC.Taller_17_TC.utils.FileProcessorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileServiceTest {


    @Mock
    FileProcessorImpl fileProcessor;

    @InjectMocks
    FileService fileService;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoadFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "text/plain", "test data".getBytes());
        String hash = "12345abcde";
        when(fileProcessor.processDataFile(file, hash)).thenReturn(ResponseFile.builder().build());
        var response = fileService.loadFile(file, hash);
        assertNotNull(response);
    }

    @Test
    void shouldThrowExceptionWhenLoadFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "text/plain", "test data".getBytes());
        String hash = "12345abcde";
        when(fileProcessor.processDataFile(file, hash)).thenThrow(IOException.class);
        assertThrows(RuntimeException.class, () -> fileService.loadFile(file, hash));
    }


}
