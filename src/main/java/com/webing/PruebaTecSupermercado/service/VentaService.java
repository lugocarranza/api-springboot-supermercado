package com.webing.PruebaTecSupermercado.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webing.PruebaTecSupermercado.dto.VentaDTO;
import com.webing.PruebaTecSupermercado.exception.NotFoundException;
import com.webing.PruebaTecSupermercado.mapper.Mapper;
import com.webing.PruebaTecSupermercado.model.DetalleVenta;
import com.webing.PruebaTecSupermercado.model.Producto;
import com.webing.PruebaTecSupermercado.model.Sucursal;
import com.webing.PruebaTecSupermercado.model.Venta;
import com.webing.PruebaTecSupermercado.repository.ProductoRepository;
import com.webing.PruebaTecSupermercado.repository.SucursalRepository;
import com.webing.PruebaTecSupermercado.repository.VentaRepository;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository repo;
    @Autowired
    private SucursalRepository sucursalRepo;
    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public List<VentaDTO> traerVentas() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        if (ventaDto == null) throw new NotFoundException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new NotFoundException("Debe indicar la sucursal");
        if (ventaDto.getDetalleVenta() == null || ventaDto.getDetalleVenta().isEmpty())
            throw new NotFoundException("Debe incluir al menos un producto");

        Sucursal sucursal = sucursalRepo.findById(ventaDto.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("No se encontró la sucursal"));

        Venta venta = Venta.builder()
                .id(ventaDto.getId())
                .fecha(ventaDto.getFecha())
                .estadoVenta(ventaDto.getEstado())
                .sucursal(sucursal)
                .total(ventaDto.getTotal())
                .build();

        List<DetalleVenta> detalles = ventaDto.getDetalleVenta().stream()
                .map(dto -> {
                    Producto producto = dto.getIdProducto() != null
                            ? productoRepo.getReferenceById(dto.getIdProducto())
                            : null;
                    return DetalleVenta.builder()
                            .cantidad(dto.getCantidad())
                            .precio(dto.getPrecio())
                            .producto(producto)
                            .venta(venta)
                            .build();
                })
                .toList();

        venta.setDetalleVenta(detalles);
        return Mapper.toDTO(repo.save(venta));
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        Venta venta = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la venta"));

        venta.setFecha(ventaDto.getFecha());
        venta.setEstadoVenta(ventaDto.getEstado());
        if (ventaDto.getIdSucursal() != null) {
            venta.setSucursal(sucursalRepo.getReferenceById(ventaDto.getIdSucursal()));
        }

        return Mapper.toDTO(repo.save(venta));
    }

    @Override
    public void eliminarVenta(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Venta no encontrada para la eliminación");
        }
        repo.deleteById(id);
    }
}
