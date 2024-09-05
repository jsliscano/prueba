package com.example.prueba.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClienteDtoResponseData {

    private String nombre;
    private String apellido;
    private String numeroIdentificacion;
    private String email;

}
