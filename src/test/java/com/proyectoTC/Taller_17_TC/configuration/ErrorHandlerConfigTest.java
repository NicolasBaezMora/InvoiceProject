package com.proyectoTC.Taller_17_TC.configuration;

import com.proyectoTC.Taller_17_TC.exceptions.GeneralServiceException;
import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class ErrorHandlerConfigTest {


    @InjectMocks
    private ErrorHandlerConfig errorHandlerConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAll() {
        Exception e = new Exception("Test exception");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> response = errorHandlerConfig.all(e, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testValidateService() {
        ValidateServiceException e = new ValidateServiceException("Test exception");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> response = errorHandlerConfig.validateService(e, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDataNotFound() {
        NoDataFoundException e = new NoDataFoundException("Test exception");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> response = errorHandlerConfig.dataNotFound(e, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGeneralServiceException() {
        GeneralServiceException e = new GeneralServiceException("Test exception");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> response = errorHandlerConfig.generalServiceException(e, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testMaxUploadFileSizeExceeded() {
        MaxUploadSizeExceededException e = new MaxUploadSizeExceededException(1);

        ResponseEntity<?> response = errorHandlerConfig.maxUploadFileSizeExceeded(e);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
    }

}
