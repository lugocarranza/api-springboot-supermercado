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

import com.webing.PruebaTecSupermercado.dto.ProductoDTO;
import com.webing.PruebaTecSupermercado.service.IProductoService;



@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    
    @Autowired
    private IProductoService serv;

    @GetMapping
    public ResponseEntity <List<ProductoDTO>> traerProductos(){
        return ResponseEntity.ok(serv.trearProductos());
    }

    @PostMapping
    public ResponseEntity <ProductoDTO> crearProducto(@RequestBody ProductoDTO dto){
        ProductoDTO prodDTO = serv.crearProducto(dto);
        return ResponseEntity.created(URI.create("/api/producto/" + prodDTO.getId())).body(prodDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ProductoDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO dto){
        ProductoDTO prodDTO = serv.actualizarProducto(id, dto);
        return ResponseEntity.ok(prodDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <ProductoDTO> eliminarProducto(@PathVariable Long id){
        serv.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    
}
