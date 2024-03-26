package com.proyectoTC.Taller_17_TC.utils;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileLoaderImplTestTest {

    @Test
    void testGetData() throws IOException {
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        InputStream mockInputStream = Mockito.mock(InputStream.class);

        when(mockFile.getInputStream()).thenReturn(mockInputStream);

        FileLoaderImpl fileLoader = new FileLoaderImpl();

        BufferedReader bufferedReader = fileLoader.getData(mockFile);

        Mockito.verify(mockFile, Mockito.times(1)).getInputStream();

        assertNotNull(bufferedReader);
    }

}
