package com.example.prueba.service;

import com.example.prueba.dto.ProductoRequest;
import com.example.prueba.entity.ProductoEntity;
import com.example.prueba.enums.TipoCuenta;
import com.example.prueba.exception.ClienteNotFoundException;

import java.math.BigDecimal;

public interface ProductoService {


    ProductoEntity crearProducto(TipoCuenta tipoCuenta, BigDecimal saldo, Boolean exentaGMF, Long clienteId) throws ClienteNotFoundException;
    ProductoEntity updateFieldEnable(Long id, ProductoRequest productoRequest);


}
