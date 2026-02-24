package com.webing.PruebaTecSupermercado.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webing.PruebaTecSupermercado.dto.ProductoDTO;
import com.webing.PruebaTecSupermercado.exception.NotFoundException;
import com.webing.PruebaTecSupermercado.mapper.Mapper;
import com.webing.PruebaTecSupermercado.model.Producto;
import com.webing.PruebaTecSupermercado.repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> trearProductos() {        
        return repo.findAll().stream().map(p -> Mapper.toDTO(p)).toList();
        
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        Producto prod = Producto.builder()
                .id(productoDto.getId())
                .nombre(productoDto.getNombre())
                .precio(productoDto.getPrecio())
                .categoria(productoDto.getCategoria())
                .stock(productoDto.getStock())
                .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        Producto prod = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el producto"));
        
        prod.setNombre(productoDto.getNombre());
        prod.setPrecio(productoDto.getPrecio());
        prod.setCategoria(productoDto.getCategoria());
        prod.setStock(productoDto.getStock());
        
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!repo.existsById(id)){
            throw new NotFoundException("Producto no encontrado para la eliminación");
        }
        repo.deleteById(id);
    }
    
}
