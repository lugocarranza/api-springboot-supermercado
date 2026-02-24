package com.webing.PruebaTecSupermercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webing.PruebaTecSupermercado.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long> {

    Optional <Producto> findByNombre(String nombre);
    
}
