package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.ControlCalidadRequestDTO;
import com.QF.Almacen_backend.Dto.ControlCalidadResponseDTO;
import com.QF.Almacen_backend.Entidades.ControlCalidad;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Entidades.Usuarios;
import com.QF.Almacen_backend.Repositorios.RepositorioControlCalidad;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import com.QF.Almacen_backend.Repositorios.RepsitorioUsuarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlCalidadService {

private final RepsitorioUsuarios repositorioUsuarios;
private final RepsitorioProductos repositorioProductos;
private final RepositorioControlCalidad repositorioControlCalidad;


public ControlCalidadService(RepsitorioUsuarios repositorioUsuarios, RepsitorioProductos repositorioProductos, RepositorioControlCalidad repositorioControlCalidad) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioProductos = repositorioProductos;
        this.repositorioControlCalidad = repositorioControlCalidad;
}

public void  registrarInspeccion (ControlCalidadRequestDTO dto) {
    Productos productos = repositorioProductos .findById(dto.idProducto()).orElseThrow(()-> new RuntimeException("Producto no encotrado"));
    Usuarios usuarios= repositorioUsuarios.findById(dto.idUsuario()).orElseThrow(()-> new RuntimeException("Usuario no encotnrado"));

    ControlCalidad control = new ControlCalidad();
    control.setProductos(productos);
    control.setUsuarios(usuarios);
    control.setResultado(dto.resultado());
    control.setObservaciones(dto.Observaciones());

    repositorioControlCalidad.save(control);

}
    public List<ControlCalidadResponseDTO> listarHistorial() {
        return repositorioControlCalidad.findAll().stream()
                .map(c -> new ControlCalidadResponseDTO(
                        c.getId(),
                        c.getProductos() != null ? c.getProductos().getNombre() : "SIN PRODUCTO",
                        c.getUsuarios() != null ? c.getUsuarios().getNombre() : "SISTEMA",
                        c.getResultado(),
                        c.getObservaciones(),
                        c.getFechaInspeccion()
                ))
                .toList();
    }

    }





