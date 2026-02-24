package com.webing.PruebaTecSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webing.PruebaTecSupermercado.dto.SucursalDTO;
import com.webing.PruebaTecSupermercado.exception.NotFoundException;
import com.webing.PruebaTecSupermercado.mapper.Mapper;
import com.webing.PruebaTecSupermercado.model.Sucursal;
import com.webing.PruebaTecSupermercado.repository.SucursalRepository;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDto) {
        Sucursal suc = Sucursal.builder()
                .id(sucursalDto.getId())
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal suc = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la sucursal"));

        suc.setNombre(sucursalDto.getNombre());
        suc.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Sucursal no encontrada para la eliminación");
        }
        repo.deleteById(id);
    }
}
