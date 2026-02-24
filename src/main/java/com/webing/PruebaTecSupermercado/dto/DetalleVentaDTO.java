package com.webing.PruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private Long idProducto;
    private String productoNombre;
    private Integer cantidad;
    private Double precio;
    private Double subtotal;

}
