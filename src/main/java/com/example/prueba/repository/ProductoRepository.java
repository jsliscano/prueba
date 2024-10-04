package com.example.prueba.repository;

import com.example.prueba.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository <ProductoEntity,Long>{

    boolean existsByNumeroCuenta(String numeroCuenta);

    ProductoEntity findByNumeroCuenta(String numeroCuenta);
    //Optional<ProductoEntity> findByNumeroCuenta(String numeroCuenta);
}
