package com.webing.PruebaTecSupermercado.service;
import java.util.List;

import com.webing.PruebaTecSupermercado.dto.ProductoDTO;

public interface IProductoService {
    public List<ProductoDTO> trearProductos();

    public ProductoDTO crearProducto(ProductoDTO productoDto);

    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);

    public void eliminarProducto(Long id);
}
