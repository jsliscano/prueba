package com.example.prueba.dto;

import com.example.prueba.enums.TipoCuenta;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductoDto {


        private TipoCuenta tipoCuenta;
        private BigDecimal saldo;
        private Boolean exentaGMF;
        private Long clienteId;

}
