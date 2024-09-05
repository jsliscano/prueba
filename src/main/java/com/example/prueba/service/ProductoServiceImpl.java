package com.example.prueba.service;

import com.example.prueba.dto.ProductoRequest;
import com.example.prueba.entity.ClienteEntity;
import com.example.prueba.entity.ProductoEntity;
import com.example.prueba.enums.EstadoCuenta;
import com.example.prueba.enums.TipoCuenta;
import com.example.prueba.exception.ClienteNotFoundException;
import com.example.prueba.exception.ProductoNotFoundException;
import com.example.prueba.repository.ProductoRepository;
import com.example.prueba.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public ProductoEntity crearProducto(TipoCuenta tipoCuenta, BigDecimal saldo, Boolean exentaGMF, Long clienteId) throws ClienteNotFoundException {
        if (tipoCuenta == TipoCuenta.AHORROS && saldo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El saldo para una cuenta de ahorros debe ser mayor a $0");
        }

        String numeroCuenta = generarNumeroCuenta(tipoCuenta);

        if (productoRepository.existsByNumeroCuenta(numeroCuenta)) {
            throw new IllegalArgumentException("El número de cuenta ya está en uso");
        }

        ClienteEntity cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("No se puede vincular el producto con el cliente con Id: " + clienteId));

        ProductoEntity producto = new ProductoEntity();
        producto.setTipoCuenta(tipoCuenta);
        producto.setNumeroCuenta(numeroCuenta);
        producto.setEstado(EstadoCuenta.ACTIVA);
        producto.setSaldo(saldo);
        producto.setExentaGMF(exentaGMF);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaModificacion(null);
        producto.setCliente(cliente);

        return productoRepository.save(producto);

    }

    @Override
    public ProductoEntity updateFieldEnable(Long id, ProductoRequest productoRequest) {
        ProductoEntity existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id " + id));

        if (productoRequest.getEstado() != null) {
            // Actualizar el estado
            existingProducto.setEstado(productoRequest.getEstado());

            // Validar el saldo si la cuenta es de ahorro y está cancelada
            if (existingProducto.getTipoCuenta() == TipoCuenta.AHORROS &&
                    productoRequest.getEstado() == EstadoCuenta.CANCELADA &&
                    existingProducto.getSaldo().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El saldo debe ser mayor a $0 para cuentas de ahorro");
            }
        }

        existingProducto.setFechaModificacion(LocalDateTime.now());

        return productoRepository.save(existingProducto);
    }


    private String generarNumeroCuenta(TipoCuenta tipoCuenta) {
        String prefijo = tipoCuenta == TipoCuenta.AHORROS ? "53" : "33";
        String numeroRestante = String.format("%08d", (int) (Math.random() * 100000000));
        return prefijo + numeroRestante;
    }
}






