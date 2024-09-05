package com.example.prueba.dto;

import com.example.prueba.enums.EstadoCuenta;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoRequest {
    private EstadoCuenta estado;
}
