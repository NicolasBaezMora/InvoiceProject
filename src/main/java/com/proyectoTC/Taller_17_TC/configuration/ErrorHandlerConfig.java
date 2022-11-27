package com.proyectoTC.Taller_17_TC.configuration;

import com.proyectoTC.Taller_17_TC.exceptions.GeneralServiceException;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(
                false,
                null,
                "Internal server error"
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateService(ValidateServiceException e, WebRequest request) {
        log.info(e.getMessage());
        WrapperResponse<?> response = new WrapperResponse<>(
                false,
                null,
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> dataNotFound(NoDataFoundException e, WebRequest request) {
        log.info(e.getMessage());
        WrapperResponse<?> response = new WrapperResponse<>(
                false,
                null,
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request) {
        log.error(e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(
                false,
                null,
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
