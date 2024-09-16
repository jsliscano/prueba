package com.example.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity

@Table(name = "transaccion")
public class TransaccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "Consignación", "Retiro", "Transferencia"

    private BigDecimal monto;

    @Column(name = "fecha_transaccion")
    private LocalDate fechaTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id_origen")
    private ProductoEntity cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_id_destino")
    private ProductoEntity cuentaDestino;
}
