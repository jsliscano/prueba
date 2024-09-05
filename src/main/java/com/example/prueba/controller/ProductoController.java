package com.example.prueba.controller;

import com.example.prueba.dto.ProductoDto;
import com.example.prueba.dto.ProductoRequest;
import com.example.prueba.entity.ProductoEntity;
import com.example.prueba.exception.ClienteNotFoundException;
import com.example.prueba.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/v1/productos")

public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<Object> crearproducto(@RequestBody ProductoDto productoDto) {
        try {
            ProductoEntity producto = productoService.crearProducto(
                    productoDto.getTipoCuenta(),
                    productoDto.getSaldo(),
                    productoDto.getExentaGMF(),
                    productoDto.getClienteId()
            );
            return ResponseEntity.ok(producto); // Retorna el producto creado con un código 200 OK
        } catch (IllegalArgumentException e) {
            // Captura y maneja excepciones relacionadas con argumentos inválidos
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (ClienteNotFoundException e) {
            // Captura y maneja el caso en el que el cliente no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno del servidor: " + e.getMessage()));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ProductoEntity updateFieldEnable(@PathVariable Long id, @RequestBody ProductoRequest productoRequest){
        return productoService.updateFieldEnable(id, productoRequest);

    }

}


