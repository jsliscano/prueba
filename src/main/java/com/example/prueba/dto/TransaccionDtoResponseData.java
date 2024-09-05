package com.example.prueba.dto;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Builder
@Getter
public class TransaccionDtoResponseData {
    private String numeroCuenta;
    private BigDecimal monto;
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
}