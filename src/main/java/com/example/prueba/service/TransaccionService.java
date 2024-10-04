package com.example.prueba.service;


import java.math.BigDecimal;

public interface TransaccionService {

    String realizarConsignacion(String numeroCuenta, BigDecimal monto);

    String realizarRetiro(String numeroCuenta, BigDecimal monto);
    String realizarTransferencia(String numeroCuentaOrigen, String numeroCuentaDestino, BigDecimal monto);
}

