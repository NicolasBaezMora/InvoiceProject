package com.proyectoTC.Taller_17_TC.response_models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {
    private Long total;
    private List<T> data;
    private int totalPages;
    private int currentPage;
}
