package com.proyectoTC.Taller_17_TC.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;

public interface FileLoader {

    BufferedReader getData(MultipartFile file) throws IOException;

}
