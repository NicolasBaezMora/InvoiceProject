package com.proyectoTC.Taller_17_TC.controllers;

import com.proyectoTC.Taller_17_TC.response_models.ResponseFile;
import com.proyectoTC.Taller_17_TC.response_models.WrapperResponse;
import com.proyectoTC.Taller_17_TC.services.FileService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Wrapper;

@RestController
@RequestMapping(path = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<WrapperResponse<ResponseFile>> uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "hash") String hash
    ) {
        ResponseFile respFile = fileService.loadFile(file, hash);

        return new WrapperResponse<>(
                true,
                respFile,
                "success"
        ).createResponse(HttpStatus.OK);

    }

}
