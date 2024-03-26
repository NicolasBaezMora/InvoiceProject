package com.proyectoTC.Taller_17_TC.services;


import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import com.proyectoTC.Taller_17_TC.utils.FileProcessorImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;

@Service
@Slf4j
public class FileService {

    @Autowired
    private FileProcessorImpl fileProcessor;

    @Transactional
    public ResponseFile loadFile(MultipartFile file, String hash) {
        try {
            Thread.sleep(7000);
            return fileProcessor.processDataFile(file, hash);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
