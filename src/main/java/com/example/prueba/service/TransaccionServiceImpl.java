package com.example.prueba.service;


import com.example.prueba.entity.ProductoEntity;
import com.example.prueba.repository.ProductoRepository;
import com.example.prueba.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;


    @Override
    public String realizarConsignacion(String numeroCuenta, BigDecimal monto) {
        ProductoEntity cuenta = productoRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            return "El monto debe ser mayor a cero";
        }
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        productoRepository.save(cuenta);
        return "Consignación exitosa";
    }

    @Override
    public String realizarRetiro(String numeroCuenta, BigDecimal monto) {
        ProductoEntity cuenta = productoRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            return "El monto debe ser mayor a cero";
        }
        if (cuenta.getSaldo().compareTo(monto) < 0) {
            return "Saldo insuficiente";
        }
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        productoRepository.save(cuenta);
        return "Retiro exitoso";
    }

    @Override
    public String realizarTransferencia(String numeroCuentaOrigen, String numeroCuentaDestino, BigDecimal monto) {
        ProductoEntity cuentaOrigen = productoRepository.findByNumeroCuenta(numeroCuentaOrigen);
        ProductoEntity cuentaDestino = productoRepository.findByNumeroCuenta(numeroCuentaDestino);

        if (cuentaOrigen == null || cuentaDestino == null) {
            return "Una o ambas cuentas no existen";
        }

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            return "El monto debe ser mayor a cero";
        }

        if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
            return "Saldo insuficiente en la cuenta de origen";
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));

        productoRepository.save(cuentaOrigen);
        productoRepository.save(cuentaDestino);

        return "Transferencia exitosa";
    }
}



