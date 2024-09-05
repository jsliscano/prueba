package com.example.prueba.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Data
@Entity
@Table(name= "clientes")

public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    private String tipoIdentificacion;

    @Column (name = "numero_identificacion", unique = true, nullable = false)
    private String numeroIdentificacion;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres.")
    private String nombre;


    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres.")
    private String apellido;


    @Column(name = "email_address", unique = true, nullable = false)
    private String email;


    private LocalDate fechaNacimiento;


    private LocalDateTime fechaCreacion;


    private LocalDateTime fechaModificacion;

}

