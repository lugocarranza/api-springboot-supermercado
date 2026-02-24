package com.webing.PruebaTecSupermercado.mapper;

import com.webing.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.webing.PruebaTecSupermercado.dto.ProductoDTO;
import com.webing.PruebaTecSupermercado.dto.SucursalDTO;
import com.webing.PruebaTecSupermercado.dto.VentaDTO;
import com.webing.PruebaTecSupermercado.model.DetalleVenta;
import com.webing.PruebaTecSupermercado.model.Producto;
import com.webing.PruebaTecSupermercado.model.Sucursal;
import com.webing.PruebaTecSupermercado.model.Venta;

import java.util.Collections;
import java.util.List;

public class Mapper {
    public static ProductoDTO toDTO(Producto p){
        if (p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .categoria(p.getCategoria())
                .build();
    }

    public static SucursalDTO toDTO(Sucursal s) {
        if (s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }

    public static VentaDTO toDTO(Venta v) {
        if (v == null) return null;

        Long idSucursal = v.getSucursal() != null ? v.getSucursal().getId() : null;

        List<DetalleVenta> detalles = v.getDetalleVenta() != null ? v.getDetalleVenta() : Collections.emptyList();

        List<DetalleVentaDTO> detalleDTOs = detalles.stream()
                .map(Mapper::toDTO).toList();  //usamos el mapper de detalleVenta a DTO             
       
        Double total = detalleDTOs.stream()
                .mapToDouble(d -> d.getSubtotal() != null ? d.getSubtotal() : 0.0)
                .sum();

        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .estado(v.getEstadoVenta())
                .idSucursal(idSucursal)
                .detalleVenta(detalleDTOs)
                .total(total)
                .build();
    }

    public static DetalleVentaDTO toDTO(DetalleVenta d) {
        if (d == null) return null;

        Long idProducto = d.getProducto() != null ? d.getProducto().getId() : null;
        String productoNombre = d.getProducto() != null ? d.getProducto().getNombre() : null;
        int cant = d.getCantidad() != null ? d.getCantidad() : 0;
        double p = d.getPrecio() != null ? d.getPrecio() : 0.0;
        Double subtotal = cant * p;

        return DetalleVentaDTO.builder()
                .id(d.getId())
                .idProducto(idProducto)
                .productoNombre(productoNombre)
                .cantidad(d.getCantidad())
                .precio(d.getPrecio())
                .subtotal(subtotal)
                .build();
    }
}
