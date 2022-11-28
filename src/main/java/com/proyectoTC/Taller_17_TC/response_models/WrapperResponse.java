package com.proyectoTC.Taller_17_TC.response_models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
public class WrapperResponse<T> {

    public boolean ok;
    public T body;
    public String message;


    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

}
