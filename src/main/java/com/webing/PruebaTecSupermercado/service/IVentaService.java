package com.webing.PruebaTecSupermercado.service;
import java.util.List;
import com.webing.PruebaTecSupermercado.dto.VentaDTO;

public interface IVentaService {
    public List<VentaDTO> traerVentas();

    public VentaDTO crearVenta(VentaDTO ventaDto);

    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto);

    public void eliminarVenta(Long id);
}
