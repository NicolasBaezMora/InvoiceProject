package com.proyectoTC.Taller_17_TC.response_models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFile {

    private Long consistent;

    private Long inconsistent;

}
