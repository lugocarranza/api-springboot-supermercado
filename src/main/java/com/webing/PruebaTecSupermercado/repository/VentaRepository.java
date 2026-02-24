package com.webing.PruebaTecSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webing.PruebaTecSupermercado.model.Venta;

public interface VentaRepository extends JpaRepository<Venta,Long> {
    
}
