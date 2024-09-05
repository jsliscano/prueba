package com.example.prueba.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.prueba.enums.TipoCuenta;
import com.example.prueba.enums.EstadoCuenta;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta")
    private TipoCuenta tipoCuenta;


    @Column(name = "numero_cuenta", unique = true, length = 10)
    private String numeroCuenta;

    // "activa", "inactiva", "cancelada"
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;


    @DecimalMin(value = "0.0", inclusive = false, message = "El saldo debe ser mayor a $0 para cuentas de ahorro")
    @Column(precision = 15, scale = 2)
    private BigDecimal saldo;

    //impuesto falso o verdadero
    private Boolean exentaGMF;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;


}



