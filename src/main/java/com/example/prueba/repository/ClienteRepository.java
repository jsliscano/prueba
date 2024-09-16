package com.example.prueba.repository;

import com.example.prueba.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <ClienteEntity,Long> {

    Optional<ClienteEntity> findByNombre(String nombre);


}


