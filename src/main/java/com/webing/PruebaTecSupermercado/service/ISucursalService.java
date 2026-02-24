package com.webing.PruebaTecSupermercado.service;

import java.util.List;

import com.webing.PruebaTecSupermercado.dto.SucursalDTO;

public interface ISucursalService {
    public List<SucursalDTO> traerSucursales();

    public SucursalDTO crearSucursal(SucursalDTO sucursalDto);

    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);

    public void eliminarSucursal(Long id);
}
