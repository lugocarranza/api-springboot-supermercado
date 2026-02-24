package com.webing.PruebaTecSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webing.PruebaTecSupermercado.model.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal,Long> {
    
}
