package com.example.prueba.repository;

import com.example.prueba.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity,Long> {

}
