package com.webing.PruebaTecSupermercado.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webing.PruebaTecSupermercado.dto.VentaDTO;
import com.webing.PruebaTecSupermercado.service.IVentaService;

@RestController
@RequestMapping("/api/venta")
public class VentasController {
    
    @Autowired
    private IVentaService serv;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas(){
        return ResponseEntity.ok(serv.traerVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO dto){
        VentaDTO ventaDTO = serv.crearVenta(dto);
        return ResponseEntity.created(URI.create("/api/venta/" + ventaDTO.getId())).body(ventaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> updateVenta(@PathVariable Long id, @RequestBody VentaDTO dto){
        VentaDTO ventaDTO = serv.actualizarVenta(id, dto);
        return ResponseEntity.ok(ventaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id){
        serv.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
    
}
