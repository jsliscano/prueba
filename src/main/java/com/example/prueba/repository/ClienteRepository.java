package com.example.prueba.repository;

import com.example.prueba.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <ClienteEntity,Long> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.nombre = :nombre")
    Optional<ClienteEntity> findByClienteNombre(String nombre);

}


