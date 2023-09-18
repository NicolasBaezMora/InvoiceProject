package com.proyectoTC.Taller_17_TC.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class FileLoaderImpl implements FileLoader {

    @Override
    public BufferedReader getData(MultipartFile file) throws IOException {
        return new BufferedReader(new InputStreamReader(file.getInputStream()));
    }

}
