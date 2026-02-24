package com.webing.PruebaTecSupermercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.webing.PruebaTecSupermercado.dto.SucursalDTO;
import com.webing.PruebaTecSupermercado.service.ISucursalService;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {
    
    @Autowired
    private ISucursalService serv;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursales() {
        return ResponseEntity.ok(serv.traerSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO dto) {
        SucursalDTO sucDTO = serv.crearSucursal(dto);
        return ResponseEntity.created(URI.create("/api/sucursal/" + sucDTO.getId())).body(sucDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(@PathVariable Long id, @RequestBody SucursalDTO dto) {
        SucursalDTO sucDTO = serv.actualizarSucursal(id, dto);
        return ResponseEntity.ok(sucDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {
        serv.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
    
}
