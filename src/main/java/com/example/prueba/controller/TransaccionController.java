package com.example.prueba.controller;

import com.example.prueba.dto.TransaccionDto;
import com.example.prueba.dto.TransaccionDtoResponse;
import com.example.prueba.dto.TransaccionDtoResponseData;
import com.example.prueba.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {


        @Autowired
        private TransaccionService transaccionService;

        @PostMapping("/consignacion")
        public ResponseEntity<TransaccionDtoResponse> realizarConsignacion(@Valid @RequestBody TransaccionDto request) {
            String resultado = transaccionService.realizarConsignacion(request.getNumeroCuenta(), request.getMonto());
            HttpStatus status = resultado.equals("Consignación exitosa") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

            TransaccionDtoResponse response = TransaccionDtoResponse.builder()
                    .code(String.valueOf(status.value()))
                    .message(resultado)
                    .data(TransaccionDtoResponseData.builder()
                            .numeroCuenta(request.getNumeroCuenta())
                            .monto(request.getMonto())
                            .build())
                    .build();
            return new ResponseEntity<>(response, status);
        }

        @PostMapping("/retiro")
        public ResponseEntity<TransaccionDtoResponse> realizarRetiro(@Valid @RequestBody TransaccionDto request) {
            String resultado = transaccionService.realizarRetiro(request.getNumeroCuenta(), request.getMonto());
            HttpStatus status = resultado.equals("Retiro exitoso") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

            TransaccionDtoResponse response = TransaccionDtoResponse.builder()
                    .code(String.valueOf(status.value()))
                    .message(resultado)
                    .data(TransaccionDtoResponseData.builder()
                            .numeroCuenta(request.getNumeroCuenta())
                            .monto(request.getMonto())
                            .build())
                    .build();
            return new ResponseEntity<>(response, status);
        }

        @PostMapping("/transferencia")
        public ResponseEntity<TransaccionDtoResponse> realizarTransferencia(@Valid @RequestBody TransaccionDto request) {
            String resultado = transaccionService.realizarTransferencia(request.getNumeroCuentaOrigen(), request.getNumeroCuentaDestino(), request.getMonto());
            HttpStatus status = resultado.equals("Transferencia exitosa") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

            TransaccionDtoResponse response = TransaccionDtoResponse.builder()
                    .code(String.valueOf(status.value()))
                    .message(resultado)
                    .data(TransaccionDtoResponseData.builder()
                            .numeroCuentaOrigen(request.getNumeroCuentaOrigen())
                            .numeroCuentaDestino(request.getNumeroCuentaDestino())
                            .monto(request.getMonto())
                            .build())
                    .build();
            return new ResponseEntity<>(response, status);
        }
    }



