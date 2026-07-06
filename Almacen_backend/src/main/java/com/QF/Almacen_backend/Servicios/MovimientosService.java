package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.MovimientoDTO;
import com.QF.Almacen_backend.Entidades.Almacenes;
import com.QF.Almacen_backend.Entidades.Movimientos;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Entidades.Usuarios;
import com.QF.Almacen_backend.Repositorios.RepsitorioMovimientos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MovimientosService {

private final RepsitorioMovimientos repsitorioMovimientos;

public MovimientosService(RepsitorioMovimientos repsitorioMovimientos) {
        this.repsitorioMovimientos = repsitorioMovimientos;
    }
    public Movimientos registrarMovimiento(Movimientos.TipoMovimiento tipo,
                                           Integer cantidad,
                                           Productos producto,
                                           Almacenes almacenOrigen,
                                           Almacenes almacenDestino,
                                           Usuarios usuario) {

        if (tipo == null) {
            throw new RuntimeException("El tipo de movimiento es obligatorio.");
        }
        if (cantidad == null || cantidad <= 0) {
            throw new RuntimeException("La cantidad del movimiento debe ser mayor a cero.");
        }
        if (producto == null) {
            throw new RuntimeException("El movimiento debe estar asociado a un producto.");
        }
        if (tipo == Movimientos.TipoMovimiento.TRANSFERENCIA
                && (almacenOrigen == null || almacenDestino == null)) {
            throw new RuntimeException("Una transferencia requiere almacén de origen y destino.");
        }

        Movimientos movimiento = new Movimientos();
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);
        movimiento.setProducto(producto);
        movimiento.setAlmacenOrigen(almacenOrigen);
        movimiento.setAlmacenDestino(almacenDestino);
        movimiento.setUsuario(usuario);

        return repsitorioMovimientos.save(movimiento);
    }

    public List<MovimientoDTO> listar() {
        return repsitorioMovimientos.findAll().stream()
                .map(MovimientoDTO::new)
                .collect(Collectors.toList());
    }

    public List<MovimientoDTO> listarPorTipo(String tipo) {
        Movimientos.TipoMovimiento tipoEnum;
        try {
            tipoEnum = Movimientos.TipoMovimiento.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo inválido. Use: ENTRADA, SALIDA, TRANSFERENCIA o MERMA");
        }

        return repsitorioMovimientos.findAll().stream()
                .filter(m -> m.getTipo() == tipoEnum)
                .map(MovimientoDTO::new)
                .collect(Collectors.toList());
    }

    public List<MovimientoDTO> listarPorProducto(Integer idProducto) {
        return repsitorioMovimientos.findAll().stream()
                .filter(m -> m.getProducto() != null && m.getProducto().getIdproducto() == idProducto)
                .map(MovimientoDTO::new)
                .collect(Collectors.toList());
    }










}
