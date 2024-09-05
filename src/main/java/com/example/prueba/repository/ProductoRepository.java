package com.example.prueba.repository;

import com.example.prueba.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository <ProductoEntity,Long>{

    boolean existsByNumeroCuenta(String numeroCuenta);

    ProductoEntity findByNumeroCuenta(String numeroCuenta);
}
