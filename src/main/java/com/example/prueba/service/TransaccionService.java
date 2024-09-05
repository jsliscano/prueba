package com.example.prueba.service;

import com.example.prueba.entity.ProductoEntity;
import com.example.prueba.entity.TransaccionEntity;

import java.math.BigDecimal;

public interface TransaccionService {
    String realizarConsignacion(String numeroCuenta, BigDecimal monto);
    String realizarRetiro(String numeroCuenta, BigDecimal monto);
    String realizarTransferencia(String numeroCuentaOrigen, String numeroCuentaDestino, BigDecimal monto);
}

