package com.proyectoTC.Taller_17_TC.utils;

import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;

public interface FileProcessor {

    ResponseFile processDataFile(MultipartFile file, String hash) throws IOException;

}
